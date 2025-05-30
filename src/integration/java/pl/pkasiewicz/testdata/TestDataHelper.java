package pl.pkasiewicz.testdata;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pkasiewicz.filterpackmate.domain.carton.CartonRepository;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonResponseDto;
import pl.pkasiewicz.filterpackmate.domain.corner.CornerRepository;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerRepository;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.ProductRepository;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;
import pl.pkasiewicz.filterpackmate.domain.side.SideRepository;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;
import pl.pkasiewicz.filterpackmate.domain.tray.TrayRepository;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayResponseDto;

import java.util.List;

@Component
public class TestDataHelper {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartonRepository cartonRepository;
    @Autowired
    TrayRepository trayRepository;
    @Autowired
    DividerRepository dividerRepository;
    @Autowired
    SideRepository sideRepository;
    @Autowired
    CornerRepository cornerRepository;
    @Autowired
    TestData testData;

    @Getter
    private List<CartonResponseDto> cartons;
    @Getter
    private List<TrayResponseDto> trays;
    @Getter
    private List<DividerResponseDto> dividers;
    @Getter
    private List<SideResponseDto> sides;
    @Getter
    private List<CornerResponseDto> corners;
    @Getter
    private List<ProductResponseDto> products;

    public void prepareDefaultProductData() {
        createDependencies();
        createProducts();
    }

    public void deleteAllData() {
        productRepository.deleteAll();
        sideRepository.deleteAll();
        dividerRepository.deleteAll();
        trayRepository.deleteAll();
        cartonRepository.deleteAll();
        cornerRepository.deleteAll();
    }

    private void createDependencies() {
        cartons = getAndSaveCartons();
        trays = getAndSaveTrays();
        dividers = getAndSaveDividers();
        sides = getAndSaveSides();
        corners = getAndSaveCorner();
    }

    private List<CornerResponseDto> getAndSaveCorner() {
        return testData.createAndSaveCorners();
    }

    private List<SideResponseDto> getAndSaveSides() {
        return testData.createAndSaveSides();
    }

    private List<DividerResponseDto> getAndSaveDividers() {
        return testData.createAndSaveDividers();
    }

    private List<TrayResponseDto> getAndSaveTrays() {
        return testData.createAndSaveTrays();
    }

    private List<CartonResponseDto> getAndSaveCartons() {
        return testData.createAndSaveCartons();
    }

    private void createProducts() {
        products = testData.createProducts(cartons, trays, dividers, sides);
    }
}
