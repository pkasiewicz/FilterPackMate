package pl.pkasiewicz.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import pl.pkasiewicz.BaseIntegrationTest;
import pl.pkasiewicz.filterpackmate.domain.user.dto.RegistrationResultDto;
import pl.pkasiewicz.filterpackmate.infrastructure.security.jwt.JwtConfigurationProperties;
import pl.pkasiewicz.filterpackmate.infrastructure.user.controller.dto.JwtResponseDto;

import java.util.Date;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private JwtConfigurationProperties jwtConfigurationProperties;

    private String validUsername = "someUser";
    private String validPassword = "somePassword";

    @Test
    @Order(1)
    void user_can_register_successfully() throws Exception {
        String responseJson = registerUser(validUsername, validPassword);

        RegistrationResultDto registrationResultDto = objectMapper.readValue(responseJson, RegistrationResultDto.class);

        assertAll(
                () -> assertThat(registrationResultDto.username()).isEqualTo(validUsername),
                () -> assertThat(registrationResultDto.id()).isNotNull()
        );
    }

    @Test
    @Order(2)
    void duplicate_registration_returns_conflict() throws Exception {
        registerUser(validUsername, validPassword); // Try again

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "%s",
                                    "password": "%s"
                                }
                                """.formatted(validUsername, validPassword)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("User already exists"));
    }

    @Test
    @Order(3)
    void user_can_login_with_valid_credentials() throws Exception {
        String token = loginAndGetToken(validUsername, validPassword);
        assertThat(token).matches(Pattern.compile("^([A-Za-z0-9-_=]+\\.)+([A-Za-z0-9-_=])+\\.?$"));
    }

    @Test
    @Order(4)
    void login_with_invalid_credentials_returns_unauthorized() throws Exception {
        mockMvc.perform(post("/api/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "%s",
                                    "password": "wrong_password"
                                }
                                """.formatted(validUsername)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Bad Credentials"));
    }

    @Test
    @Order(5)
    void protected_endpoint_requires_authentication() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(6)
    void protected_endpoint_allows_authenticated_user() throws Exception {
        String token = loginAndGetToken(validUsername, validPassword);

        mockMvc.perform(get("/api/products")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    void invalid_token_returns_unauthorized() throws Exception {
        mockMvc.perform(get("/api/products")
                        .header("Authorization", "Bearer invalidToken"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(8)
    void expired_token_returns_unauthorized() throws Exception {
        String expiredToken = generateExpiredToken(); // see below
        mockMvc.perform(get("/api/products")
                        .header("Authorization", "Bearer " + expiredToken))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(9)
    void valid_token_allows_access() throws Exception {
        String validToken = generateValidJwtToken("someUser");
        mockMvc.perform(get("/api/products")
                        .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isOk());
    }

    private String registerUser(String username, String password) throws Exception {
        String requestJson = """
                {
                    "username": "%s",
                    "password": "%s"
                }
                """.formatted(username, password);

        return mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String loginAndGetToken(String username, String password) throws Exception {
        String requestJson = """
                {
                    "username": "%s",
                    "password": "%s"
                }
                """.formatted(username, password);

        String responseJson = mockMvc.perform(post("/api/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JwtResponseDto jwt = objectMapper.readValue(responseJson, JwtResponseDto.class);
        return jwt.token();
    }

    private String generateValidJwtToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfigurationProperties.secret());

        long now = System.currentTimeMillis();
        long expirationMs = jwtConfigurationProperties.expirationDays() * 24 * 60 * 60 * 1000L;

        return JWT.create()
                .withSubject(username)
                .withIssuer(jwtConfigurationProperties.issuer())
                .withExpiresAt(new Date(now + expirationMs))
                .sign(algorithm);
    }

    private String generateExpiredToken() {
        Algorithm algorithm = Algorithm.HMAC256("your-secret-key");
        return JWT.create()
                .withSubject("someUser")
                .withExpiresAt(new Date(System.currentTimeMillis() - 1000L)) // expired 1 sec ago
                .sign(algorithm);
    }
}
