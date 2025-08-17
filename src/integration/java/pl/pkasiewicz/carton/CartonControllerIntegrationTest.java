package pl.pkasiewicz.carton;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pkasiewicz.CrudModuleIntegrationTest;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonRequestDto;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonResponseDto;
import pl.pkasiewicz.testdata.TestDataHelper;

class CartonControllerIntegrationTest extends CrudModuleIntegrationTest<CartonRequestDto, CartonResponseDto> {

    @Autowired
    private TestDataHelper testDataHelper;

    @BeforeEach
    void prepareTestData() {
        testDataHelper.deleteAllData();
        testDataHelper.prepareDefaultProductData();
    }

    @Override
    protected String getBaseUrl() {
        return "/api/cartons";
    }

    @Override
    protected Class<CartonResponseDto> getResponseDtoClass() {
        return CartonResponseDto.class;
    }

    @Override
    protected CartonRequestDto getValidExampleDto() {
        return CartonRequestDto.builder()
                .name("PCA-100")
                .build();
    }

    @Override
    protected String getNotFoundErrorMsg() {
        return "Carton not found";
    }

    @Override
    protected String getAlreadyExistsErrorMsg() {
        return "Carton already exists";
    }
}
