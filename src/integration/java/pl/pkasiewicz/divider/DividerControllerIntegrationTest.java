package pl.pkasiewicz.divider;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pkasiewicz.CrudModuleIntegrationTest;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;
import pl.pkasiewicz.testdata.TestDataHelper;

class DividerControllerIntegrationTest extends CrudModuleIntegrationTest<DividerRequestDto, DividerResponseDto> {

    @Autowired
    private TestDataHelper testDataHelper;

    @BeforeEach
    void prepareTestData() {
        testDataHelper.deleteAllData();
        testDataHelper.prepareDefaultProductData();
    }

    @Override
    protected String getBaseUrl() {
        return "/dividers";
    }

    @Override
    protected Class<DividerResponseDto> getResponseDtoClass() {
        return DividerResponseDto.class;
    }

    @Override
    protected DividerRequestDto getValidExampleDto() {
        return DividerRequestDto.builder()
                .name("DIV-100")
                .quantityPerPallet(4)
                .build();
    }

    @Override
    protected String getNotFoundErrorMsg() {
        return "Divider not found";
    }

    @Override
    protected String getAlreadyExistsErrorMsg() {
        return "Divider already exists";
    }
}
