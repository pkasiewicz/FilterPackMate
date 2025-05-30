package pl.pkasiewicz.tray;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pkasiewicz.CrudModuleIntegrationTest;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayRequestDto;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayResponseDto;
import pl.pkasiewicz.testdata.TestDataHelper;

class TrayControllerIntegrationTest extends CrudModuleIntegrationTest<TrayRequestDto, TrayResponseDto> {

    @Autowired
    private TestDataHelper testDataHelper;

    @BeforeEach
    void prepareTestData() {
        testDataHelper.deleteAllData();
        testDataHelper.prepareDefaultProductData();
    }

    @Override
    protected String getBaseUrl() {
        return "/trays";
    }

    @Override
    protected Class<TrayResponseDto> getResponseDtoClass() {
        return TrayResponseDto.class;
    }

    @Override
    protected TrayRequestDto getValidExampleDto() {
        return TrayRequestDto.builder()
                .name("DE-199")
                .build();
    }

    @Override
    protected String getNotFoundErrorMsg() {
        return "Tray not found";
    }

    @Override
    protected String getAlreadyExistsErrorMsg() {
        return "Tray already exists";
    }
}
