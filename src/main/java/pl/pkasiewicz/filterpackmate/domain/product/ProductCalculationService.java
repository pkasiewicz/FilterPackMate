package pl.pkasiewicz.filterpackmate.domain.product;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.carton.exceptions.CartonNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerMapper;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerDto;
import pl.pkasiewicz.filterpackmate.domain.side.SideMapper;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideDto;
import pl.pkasiewicz.filterpackmate.domain.tray.excteptions.TrayNotFoundException;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class ProductCalculationService {

    private static final int NUMBER_OF_CORNERS_FOR_PALLET = 4;
    private static final int NUMBER_OF_SIDES_FOR_PALLET = 2;

    PackagingCalculationDto calculatePackingMaterials(Product product, int productQty) {
        if (product.getCarton() == null) throw new CartonNotFoundException("Carton is required");
        if (product.getTray() == null) throw new TrayNotFoundException("Tray is required");

        int neededCartons = calculateNeededCartons(product, productQty);
        int neededTrays = calculateNeededTrays(neededCartons);

        int palletsNeeded = calculateNeededPallets(product, productQty);

        Set<DividerDto> neededDividers = getNeededDividers(product);
        Set<SideDto> neededSides = calculateNeededSides(product, palletsNeeded);

        Integer neededCorner = null;
        if (product.getCorner() != null) {
            neededCorner = calculateNeededCorner(palletsNeeded);
        }

        return new PackagingCalculationDto(
                product.getId(),
                product.getName(),
                productQty,
                product.getCarton().getName(),
                neededCartons,
                product.getTray().getName(),
                neededTrays,
                neededDividers,
                neededSides,
                neededCorner);
    }

    private int calculateNeededCartons(Product product, int productQty) {
        int filtersPerCarton = product.getFiltersPerCarton();
        if (filtersPerCarton == 0) throw new IllegalArgumentException("Cartons per pallet cannot be zero");
        return (int) Math.ceil((double) productQty / filtersPerCarton);
    }

    private int calculateNeededTrays(int neededCartons) {
        return neededCartons * 2;
    }

    private int calculateNeededPallets(Product product, int productQty) {
        int filtersPerPallet = product.getFiltersPerPallet();
        if (filtersPerPallet == 0) throw new IllegalArgumentException("Filters per pallet cannot be zero");
        return (int) Math.ceil((double) productQty / filtersPerPallet);
    }

    private Set<DividerDto> getNeededDividers(Product product) {
        return Optional.ofNullable(product.getDividers())
                .orElse(Collections.emptyList())
                .stream()
                .map(DividerMapper::mapToDividerDto)
                .collect(Collectors.toSet());
    }

    private Set<SideDto> calculateNeededSides(Product product, int palletsNeeded) {
        return Optional.ofNullable(product.getSides())
                .orElse(Collections.emptyList())
                .stream()
                .map(SideMapper::mapToSideDto)
                .map(sideDto -> sideDto.toBuilder().sideQty(palletsNeeded * NUMBER_OF_SIDES_FOR_PALLET).build())
                .collect(Collectors.toSet());
    }

    private int calculateNeededCorner(int pallets) {
        return pallets * NUMBER_OF_CORNERS_FOR_PALLET;
    }
}