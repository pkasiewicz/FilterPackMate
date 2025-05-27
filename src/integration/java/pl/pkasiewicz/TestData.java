package pl.pkasiewicz;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pkasiewicz.filterpackmate.domain.carton.CartonFacade;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonRequestDto;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonResponseDto;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerFacade;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.ProductFacade;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;
import pl.pkasiewicz.filterpackmate.domain.side.SideFacade;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideRequestDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;
import pl.pkasiewicz.filterpackmate.domain.tray.TrayFacade;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayRequestDto;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayResponseDto;

import java.util.List;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class TestData {

    @Autowired
    private final ProductFacade productFacade;
    @Autowired
    private final CartonFacade cartonFacade;
    @Autowired
    private final TrayFacade trayFacade;
    @Autowired
    private final DividerFacade dividerFacade;
    @Autowired
    private final SideFacade sideFacade;

    // --- CARTONS ---
    List<CartonResponseDto> createAndSaveCartons() {
        return Stream.of(
                        CartonRequestDto.builder().name("PC-A-19").build(),
                        CartonRequestDto.builder().name("ERBL205190").build(),
                        CartonRequestDto.builder().name("PC-A-8").build(),
                        CartonRequestDto.builder().name("PC-A-38").build()
                )
                .map(cartonFacade::saveCarton)
                .toList();
    }

    // --- TRAYS ---
    List<TrayResponseDto> createAndSaveTrays() {
        return Stream.of(
                        TrayRequestDto.builder().name("DE163").build(),
                        TrayRequestDto.builder().name("BE205190").build(),
                        TrayRequestDto.builder().name("DE235162").build(),
                        TrayRequestDto.builder().name("DE206153").build()
                )
                .map(trayFacade::saveTray)
                .toList();
    }

    // --- DIVIDERS ---
    List<DividerResponseDto> createAndSaveDividers() {
        return Stream.of(
                        DividerRequestDto.builder().name("Divider-1").quantityPerPallet(4).build(),
                        DividerRequestDto.builder().name("Divider-2").quantityPerPallet(4).build(),
                        DividerRequestDto.builder().name("Divider-3").quantityPerPallet(4).build()
                ).map(dividerFacade::saveDivider)
                .toList();
    }

    // --- SIDES ---
    List<SideResponseDto> createAndSaveSides() {
        return Stream.of(
                        SideRequestDto.builder().name("Side-A").build(),
                        SideRequestDto.builder().name("Side-B").build()
                ).map(sideFacade::saveSide)
                .toList();
    }

    public List<ProductResponseDto> createProducts(
            List<CartonResponseDto> cartons,
            List<TrayResponseDto> trays,
            List<DividerResponseDto> dividers,
            List<SideResponseDto> sides) {

        return Stream.of(
                        ProductRequestDto.builder()
                                .name("BA24")
                                .filtersPerCarton(6)
                                .cartonsPerPallet(20)
                                .filtersPerPallet(120)
                                .cartonId(cartons.get(0).id())
                                .trayId(trays.get(0).id())
                                .palletType("EURO")
                                .dividerIds(List.of(dividers.get(0).id(), dividers.get(1).id()))
                                .sideIds(List.of(sides.get(0).id()))
                                .cornerId(null)
                                .build(),

                        ProductRequestDto.builder()
                                .name("BA25")
                                .filtersPerCarton(null)
                                .cartonsPerPallet(null)
                                .filtersPerPallet(105)
                                .cartonId(cartons.get(1).id())
                                .trayId(trays.get(1).id())
                                .palletType("EURO")
                                .dividerIds(List.of(dividers.get(2).id()))
                                .sideIds(List.of(sides.get(0).id(), sides.get(1).id()))
                                .cornerId(null)
                                .build(),

                        ProductRequestDto.builder()
                                .name("BA26")
                                .filtersPerCarton(4)
                                .cartonsPerPallet(24)
                                .filtersPerPallet(96)
                                .cartonId(cartons.get(2).id())
                                .trayId(trays.get(2).id())
                                .palletType("EURO")
                                .dividerIds(null)
                                .sideIds(List.of(sides.get(0).id()))
                                .cornerId(null)
                                .build(),

                        ProductRequestDto.builder()
                                .name("BA27")
                                .filtersPerCarton(4)
                                .cartonsPerPallet(20)
                                .filtersPerPallet(80)
                                .cartonId(cartons.get(3).id())
                                .trayId(trays.get(3).id())
                                .palletType("EURO")
                                .dividerIds(List.of(dividers.get(0).id(), dividers.get(1).id()))
                                .sideIds(null)
                                .cornerId(null)
                                .build()
                ).map(productFacade::saveProduct)
                .toList();
    }
}
