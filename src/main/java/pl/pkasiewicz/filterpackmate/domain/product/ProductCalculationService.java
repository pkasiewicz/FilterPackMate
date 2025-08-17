package pl.pkasiewicz.filterpackmate.domain.product;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.carton.exceptions.CartonNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.corner.CornerMapper;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerDto;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerMapper;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.PackagingCalculationDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductCornerDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductSideDto;
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

    PackagingCalculationDto calculatePackingMaterials(Product product, int productQty, boolean isLotted) {
        if (product.getCarton() == null) throw new CartonNotFoundException("Carton is required");
        if (product.getTray() == null) throw new TrayNotFoundException("Tray is required");

        int neededCartons = calculateNeededCartons(product, productQty);
        int neededTrays = calculateNeededTrays(product, neededCartons);

        int palletsNeeded = calculateNeededPallets(product, productQty);

        Set<DividerDto> neededDividers = getNeededDividers(product);
        Set<ProductSideDto> neededSides = calculateNeededSides(product, palletsNeeded, isLotted);
        Set<ProductCornerDto> neededCorners = calculateNeededCorners(product, palletsNeeded, isLotted);

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
                neededCorners,
                product.getPallet().name(),
                palletsNeeded
        );
    }

    private int calculateNeededCartons(Product product, int productQty) {
        if (product.getFiltersPerCarton() == null) //todo
            return 150;
        else {
            int filtersPerCarton = product.getFiltersPerCarton();
            if (filtersPerCarton == 0) throw new IllegalArgumentException("Cartons per pallet cannot be zero");
            return (int) Math.ceil((double) productQty / filtersPerCarton);
        }
    }

    private int calculateNeededTrays(Product product, int neededCartons) {
        if (product.getFiltersPerCarton() == null) //todo
            return 200;
        else
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

    private Set<ProductSideDto> calculateNeededSides(Product product, int palletsNeeded, boolean isLotted) {
        if (!isLotted) {
            return Optional.ofNullable(product.getProductSides())
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(productSide -> !productSide.isLotted())
                    .map(productSide -> mapToProductSideDto(productSide, palletsNeeded))
                    .collect(Collectors.toSet());
        } else {
            return Optional.ofNullable(product.getProductSides())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(productSide -> mapToProductSideDto(productSide, palletsNeeded))
                    .collect(Collectors.toSet());
        }
    }

    private ProductSideDto mapToProductSideDto(ProductSide productSide, int palletsNeeded) {
        SideDto sideDto = SideMapper.mapToSideDto(productSide.getSide())
                .toBuilder()
                .sideQty(palletsNeeded * NUMBER_OF_SIDES_FOR_PALLET)
                .build();

        return ProductSideDto.builder()
                .side(sideDto)
                .isLotted(productSide.isLotted())
                .build();
    }


    private Set<ProductCornerDto> calculateNeededCorners(Product product, int palletsNeeded, boolean isLotted) {
        if (!isLotted) {
            return Optional.ofNullable(product.getProductCorners())
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(productCorner -> !productCorner.isLotted())
                    .map(productCorner -> mapToProductCornerDto(productCorner, palletsNeeded))
                    .collect(Collectors.toSet());
        } else {
            return Optional.ofNullable(product.getProductCorners())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(productCorner -> mapToProductCornerDto(productCorner, palletsNeeded))
                    .collect(Collectors.toSet());
        }
    }

    private ProductCornerDto mapToProductCornerDto(ProductCorner productCorner, int palletsNeeded) {
        CornerDto cornerDto = CornerMapper.mapToCornerDto(productCorner.getCorner())
                .toBuilder()
                .cornerQty(palletsNeeded * NUMBER_OF_CORNERS_FOR_PALLET)
                .build();

        return ProductCornerDto.builder()
                .corner(cornerDto)
                .isLotted(productCorner.isLotted())
                .build();
    }















//    <T> Set<T> calculateItems(List<?> items, Function<?, Boolean> isLottedCheck, BiFunction<?, Integer, T> mapper, boolean isLotted) {
//        return Optional.ofNullable(items)
//                .orElse(Collections.emptyList())
//                .stream()
//                .filter(item -> isLottedCheck.apply(item) == isLotted || !isLottedCheck.apply(item))
//                .map(item -> mapper.apply(item, palletsNeeded))
//                .collect(Collectors.toSet());
//    }

//    calculateItems(product.getProductSides(), ps -> ps.isLotted(), this::mapToProductSideDto, isLotted);
}