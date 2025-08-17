package pl.pkasiewicz;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pl.pkasiewicz.filterpackmate.infrastructure.user.controller.dto.JwtResponseDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public abstract class CrudModuleIntegrationTest<T, R> extends BaseIntegrationTest {

    protected String token;

    protected abstract String getBaseUrl();

    protected abstract Class<R> getResponseDtoClass();

    protected abstract T getValidExampleDto();

    protected abstract String getNotFoundErrorMsg();

    protected abstract String getAlreadyExistsErrorMsg();

    @BeforeEach
    void loginAndGetToken() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/register")
                .content("""
            {
                "username": "someUser",
                "password": "somePassword"
            }
            """)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().is(anyOf(
                equalTo(HttpStatus.CREATED.value()),
                equalTo(HttpStatus.CONFLICT.value())
        )));

        // Get JWT Token
        ResultActions succeedLoginRequest = mockMvc.perform(post("/api/token")
                .content("""
                        {
                            "username": "someUser",
                            "password": "somePassword"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        String succeedLoginRequestJson = succeedLoginRequest.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JwtResponseDto jwtResponseDto = objectMapper.readValue(succeedLoginRequestJson, JwtResponseDto.class);
        this.token = jwtResponseDto.token();
    }

    @Test
    void user_can_perform_crud_operations() throws Exception {
        R created = createEntity(token);

        Long id = getIdFrom(created);

        getEntityById(token, id, created);

        getAllEntities(token);

//        deleteEntity(token, id);
    }

    protected R createEntity(String token) throws Exception {
        String json = objectMapper.writeValueAsString(getValidExampleDto());
        String responseJson = mockMvc.perform(post(getBaseUrl() + "/add")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(responseJson, getResponseDtoClass());
    }

    protected void getEntityById(String token, Long id, R expected) throws Exception {
        String responseJson = mockMvc.perform(get(getBaseUrl() + "/" + id)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        R actual = objectMapper.readValue(responseJson, getResponseDtoClass());
        assertThat(actual).isEqualTo(expected);
    }

    protected void getAllEntities(String token) throws Exception {
        mockMvc.perform(get(getBaseUrl())
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(Matchers.greaterThanOrEqualTo(0))));
    }

    @Test
    void getting_non_existing_entity_returns_not_found() throws Exception {
        mockMvc.perform(get(getBaseUrl() + "/9999")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                        {
                            "message": "%s",
                            "status": "NOT_FOUND"
                        }
                        """.formatted(getNotFoundErrorMsg())));
    }

    @Test
    void creating_duplicate_entity_returns_conflict() throws Exception {
        T dto = getValidExampleDto();

        createEntity(token);

        String json = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post(getBaseUrl() + "/add")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict())
                .andExpect(content().json("""
                {
                    "message": "%s",
                    "status": "CONFLICT"
                }
                """.formatted(getAlreadyExistsErrorMsg())));
    }

    protected Long getIdFrom(R dto) {
        return (Long) ReflectionTestUtils.getField(dto, "id");
    }

//    protected void deleteEntity(String token, Long id) throws Exception {
//        mockMvc.perform(delete(getBaseUrl() + "/" + id)
//                        .header("Authorization", "Bearer " + token))
//                .andExpect(status().isNoContent());
//    }
}