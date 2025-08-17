package pl.pkasiewicz.corner;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pkasiewicz.CrudModuleIntegrationTest;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerResponseDto;
import pl.pkasiewicz.testdata.TestDataHelper;

class CornerControllerIntegrationTest extends CrudModuleIntegrationTest<CornerRequestDto, CornerResponseDto> {

    @Autowired
    private TestDataHelper testDataHelper;

    @BeforeEach
    void prepareTestData() {
        testDataHelper.deleteAllData();
        testDataHelper.prepareDefaultProductData();
    }

    @Override
    protected String getBaseUrl() {
        return "/api/corners";
    }

    @Override
    protected Class<CornerResponseDto> getResponseDtoClass() {
        return CornerResponseDto.class;
    }

    @Override
    protected CornerRequestDto getValidExampleDto() {
        return CornerRequestDto.builder()
                .name("CP-1000")
                .build();
    }

    @Override
    protected String getNotFoundErrorMsg() {
        return "Corner not found";
    }

    @Override
    protected String getAlreadyExistsErrorMsg() {
        return "Corner already exists";
    }
}
