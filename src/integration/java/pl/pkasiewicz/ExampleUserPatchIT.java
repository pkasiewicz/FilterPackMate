package pl.pkasiewicz;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pl.pkasiewicz.filterpackmate.domain.carton.CartonRepository;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonResponseDto;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerRepository;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.Pallet;
import pl.pkasiewicz.filterpackmate.domain.product.ProductRepository;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;
import pl.pkasiewicz.filterpackmate.domain.side.SideRepository;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;
import pl.pkasiewicz.filterpackmate.domain.tray.TrayRepository;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayResponseDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.RegistrationResultDto;
import pl.pkasiewicz.filterpackmate.infrastructure.user.controller.dto.JwtResponseDto;

import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExampleUserPatchIT extends BaseIntegrationTest {

    @Autowired
    private TestData testData;

    @BeforeEach
    void setUp(
            @Autowired ProductRepository productRepository,
            @Autowired CartonRepository cartonRepository,
            @Autowired TrayRepository trayRepository,
            @Autowired DividerRepository dividerRepository,
            @Autowired SideRepository sideRepository) {

        productRepository.deleteAll();
        cartonRepository.deleteAll();
        trayRepository.deleteAll();
        dividerRepository.deleteAll();
        sideRepository.deleteAll();
    }

    @Test
    public void user_want_to_see_products_and_add_new_one_but_have_to_be_logged_in() throws Exception {

        // typical path: user want to check products and packages for production but have to be logged in, one of products is new so he have to save it in database

        // step 1: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned UNAUTHORIZED(401)
        // given && when
        ResultActions failedLoginRequest = mockMvc.perform(post("/token")
                .content("""
                        {
                            "username": "someUser",
                            "password": "somePassword"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        failedLoginRequest.andExpect(status().isUnauthorized())
                .andExpect(content().json("""
                        {
                            "message": "Bad Credentials",
                            "status": "UNAUTHORIZED"
                        }
                        """.trim())
                );


        // step 2: user made GET /products with no jwt token and system returned UNAUTHORIZED(401)
        // given && when
        ResultActions failedGetOffersRequest = mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        failedGetOffersRequest.andExpect(status().isForbidden());


        // step 3: user made POST /register with username=someUser, password=somePassword and system registered user with status CREATED(201)
        // given && when
        ResultActions registeredUser = mockMvc.perform(post("/register")
                .content("""
                        {
                            "username": "someUser",
                            "password": "somePassword"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        String registeredUserJson = registeredUser.andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        RegistrationResultDto registrationResultDto = objectMapper.readValue(registeredUserJson, RegistrationResultDto.class);

        assertAll(
                () -> assertThat(registrationResultDto.username()).isEqualTo("someUser"),
                () -> assertThat(registrationResultDto.id()).isNotNull()
        );


        // step 4: user made POST /register with username=someUser, password=somePassword and system returned user already exists with status CONFLICT(409)
        // given && when
        ResultActions duplicateRegistrationRequest = mockMvc.perform(post("/register")
                .content("""
                        {
                            "username": "someUser",
                            "password": "somePassword"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        duplicateRegistrationRequest.andExpect(status().isConflict());


        // step 5: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC
        // given && when
        ResultActions succeedLoginRequest = mockMvc.perform(post("/token")
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
        String token = jwtResponseDto.token();

        assertAll(
                () -> assertThat(jwtResponseDto.username()).isEqualTo("someUser"),
                () -> assertThat(jwtResponseDto.token()).matches(Pattern.compile("^([A-Za-z0-9-_=]+\\.)+([A-Za-z0-9-_=])+\\.?$"))
        );


        // step 6: four products was added to database
        List<CartonResponseDto> cartons = testData.createAndSaveCartons();
        List<TrayResponseDto> trays = testData.createAndSaveTrays();
        List<DividerResponseDto> dividers = testData.createAndSaveDividers();
        List<SideResponseDto> sides = testData.createAndSaveSides();

        List<ProductResponseDto> products = testData.createProducts(cartons, trays, dividers, sides);


        // step 7: user made GET /products with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 products
        // given && when
        ResultActions performGetResultWithFourProducts = mockMvc.perform(get("/products")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        String jsonWithFourProducts = performGetResultWithFourProducts.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<ProductResponseDto> fourProductsInDatabase = objectMapper.readValue(jsonWithFourProducts, new TypeReference<>() {
        });

        assertThat(fourProductsInDatabase).hasSize(4);
        assertThat(fourProductsInDatabase).containsExactlyInAnyOrder(products.get(0), products.get(1), products.get(2), products.get(3));


        // step 8: user made POST /products with header “Authorization: Bearer AAAA.BBBB.CCC” and products and system returned CREATED(201) with saved offer
        // given && when
        ResultActions performPostRequestWithProduct = mockMvc.perform(post("/products")
                .content("""
                            {
                                "name": "A1",
                                "filtersPerCarton": 1,
                                "cartonsPerPallet": 2,
                                "filtersPerPallet": 3,
                                "cartonId": 40,
                                "trayId": 37,
                                "palletType": "EURO",
                                "dividerIds": null,
                                "sideIds": null,
                                "cornerId": null
                            }
                        """.trim())
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        String jsonWithSavedProduct = performPostRequestWithProduct.andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ProductResponseDto savedProduct = objectMapper.readValue(jsonWithSavedProduct, ProductResponseDto.class);

        assertAll(
                () -> assertThat(savedProduct.id()).isNotNull(),
                () -> assertThat(savedProduct.name()).isEqualTo("A1"),
                () -> assertThat(savedProduct.filtersPerCarton()).isEqualTo(1),
                () -> assertThat(savedProduct.cartonsPerPallet()).isEqualTo(2),
                () -> assertThat(savedProduct.filtersPerPallet()).isEqualTo(3),
                () -> assertThat(savedProduct.cartonId()).isEqualTo(cartons.get(0).id()),
                () -> assertThat(savedProduct.cartonName()).isEqualTo("PC-A-19"),
                () -> assertThat(savedProduct.trayId()).isEqualTo(trays.get(0).id()),
                () -> assertThat(savedProduct.trayName()).isEqualTo("DE163"),
                () -> assertThat(savedProduct.palletType()).isEqualTo(Pallet.EURO.name()),
                () -> assertThat(savedProduct.dividers()).isEmpty(),
                () -> assertThat(savedProduct.sides()).isEmpty(),
                () -> assertThat(savedProduct.cornerId()).isNull()
        );


        // step 9: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 5 products
        // given && when
        ResultActions performGetResultWithFiveProducts = mockMvc.perform(get("/products")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        String jsonWithFiveProducts = performGetResultWithFiveProducts.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<ProductResponseDto> fiveProductsInDatabase = objectMapper.readValue(jsonWithFiveProducts, new TypeReference<>() {
        });

        assertThat(fiveProductsInDatabase).hasSize(5);
        assertThat(fiveProductsInDatabase).containsExactlyInAnyOrder(products.get(0), products.get(1), products.get(2), products.get(3), savedProduct);


        // step 10: user made GET /products/9999 and system returned NOT_FOUND(404) with message “Product not found”
        // given && when
        ResultActions performGetResultWithNotExistingId = mockMvc.perform(get("/products/9999")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        performGetResultWithNotExistingId.andExpect(status().isNotFound())
                .andExpect(content().json("""
                        {
                            "message": "Product not found",
                            "status": "NOT_FOUND"
                        }
                        """.trim())
                );


        // step 11: user made GET /products/1000 and system returned OK(200) with product
        // given
        ProductResponseDto productInDatabase = products.get(0);

        // when
        ResultActions performGetResultWithExistingId = mockMvc.perform(get("/products/" + productInDatabase.id())
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        String jsonWithFoundOffer = performGetResultWithExistingId.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ProductResponseDto foundProduct = objectMapper.readValue(jsonWithFoundOffer, ProductResponseDto.class);

        assertThat(foundProduct).isEqualTo(productInDatabase);
    }
}
