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


        // step 2: user made GET /products with no jwt token and system returned UNAUTHORIZED(401)


        // step 3: user made POST /register with username=someUser, password=somePassword and system registered user with status CREATED(201)


        // step 4: user made POST /register with username=someUser, password=somePassword and system returned user already exists with status CONFLICT(409)


        // step 5: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC


        // step 6: four products was added to database


        // step 7: user made GET /products with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 products


        // step 8: user made POST /products with header “Authorization: Bearer AAAA.BBBB.CCC” and products and system returned CREATED(201) with saved offer


        // step 9: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 5 products


        // step 10: user made GET /products/9999 and system returned NOT_FOUND(404) with message “product with id 9999 not found”


        // step 11: user made GET /products/1000 and system returned OK(200) with products


    }
}
