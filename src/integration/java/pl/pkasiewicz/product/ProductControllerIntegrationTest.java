package pl.pkasiewicz.product;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pkasiewicz.CrudModuleIntegrationTest;
import pl.pkasiewicz.testdata.TestDataHelper;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;

class ProductControllerIntegrationTest extends CrudModuleIntegrationTest<ProductRequestDto, ProductResponseDto> {

    @Autowired
    private TestDataHelper testDataHelper;

    @BeforeEach
    void prepareTestData() {
        testDataHelper.deleteAllData();
        testDataHelper.prepareDefaultProductData();
    }

    @Override
    protected String getBaseUrl() {
        return "/api/products";
    }

    @Override
    protected Class<ProductResponseDto> getResponseDtoClass() {
        return ProductResponseDto.class;
    }

    @Override
    protected ProductRequestDto getValidExampleDto() {
        return ProductRequestDto.builder()
                .name("A1")
                .filtersPerCarton(1)
                .cartonsPerPallet(2)
                .filtersPerPallet(3)
                .cartonId(testDataHelper.getCartons().get(0).id())
                .trayId(testDataHelper.getTrays().get(0).id())
                .palletType("EURO")
                .dividerIds(null)
                .sideIds(null)
                .cornerId(null)
                .build();
    }

    @Override
    protected String getNotFoundErrorMsg() {
        return "Product not found";
    }

    @Override
    protected String getAlreadyExistsErrorMsg() {
        return "Product already exists";
    }
}