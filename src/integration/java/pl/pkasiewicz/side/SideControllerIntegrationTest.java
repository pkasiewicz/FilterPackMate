package pl.pkasiewicz.side;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pkasiewicz.CrudModuleIntegrationTest;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideRequestDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;
import pl.pkasiewicz.testdata.TestDataHelper;

class SideControllerIntegrationTest extends CrudModuleIntegrationTest<SideRequestDto, SideResponseDto> {

    @Autowired
    private TestDataHelper testDataHelper;

    @BeforeEach
    void prepareTestData() {
        testDataHelper.deleteAllData();
        testDataHelper.prepareDefaultProductData();
    }

    @Override
    protected String getBaseUrl() {
        return "/sides";
    }

    @Override
    protected Class<SideResponseDto> getResponseDtoClass() {
        return SideResponseDto.class;
    }

    @Override
    protected SideRequestDto getValidExampleDto() {
        return SideRequestDto.builder()
                .name("BE1000A")
                .build();
    }

    @Override
    protected String getNotFoundErrorMsg() {
        return "Side not found";
    }

    @Override
    protected String getAlreadyExistsErrorMsg() {
        return "Side already exists";
    }
}
