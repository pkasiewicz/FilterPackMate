package pl.pkasiewicz.filterpackmate.domain.product;

import pl.pkasiewicz.filterpackmate.domain.corner.CornerMapper;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerMapper;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductCornerDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductSideDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductSummaryDto;
import pl.pkasiewicz.filterpackmate.domain.side.SideMapper;

import java.util.List;
import java.util.Optional;

public class ProductMapper {

    static ProductResponseDto mapToDto(Product entity) {
        return ProductResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cartonId(entity.getCarton().getId())
                .cartonName(entity.getCarton().getName())
                .trayId(entity.getTray().getId())
                .trayName(entity.getTray().getName())
                .filtersPerCarton(entity.getFiltersPerCarton())
                .cartonsPerPallet(entity.getCartonsPerPallet())
                .filtersPerPallet(entity.getFiltersPerPallet())
                .palletType(entity.getPallet().name())
                .dividers(
                        Optional.ofNullable(entity.getDividers())
                                .orElseGet(List::of)
                                .stream()
                                .map(DividerMapper::mapToDividerDto)
                                .toList()
                )
                .productSides(
                        Optional.ofNullable(entity.getProductSides())
                                .orElseGet(List::of)
                                .stream()
                                .map(ProductMapper::mapToProductSideDto)
                                .toList())
                .productCorners(
                        Optional.ofNullable(entity.getProductCorners())
                                .orElseGet(List::of)
                                .stream()
                                .map(ProductMapper::mapToProductCornerDto)
                                .toList()
                )
                .build();
    }

    public static ProductSummaryDto mapToProductSummaryDto(Product entity) {
        return ProductSummaryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    private static ProductSideDto mapToProductSideDto(ProductSide productSide) {
        return ProductSideDto.builder()
                .side(SideMapper.mapToSideDto(productSide.getSide()))
                .isLotted(productSide.isLotted())
                .build();
    }

    private static ProductCornerDto mapToProductCornerDto(ProductCorner productCorner) {
        return ProductCornerDto.builder()
                .corner(CornerMapper.mapToCornerDto(productCorner.getCorner()))
                .isLotted(productCorner.isLotted())
                .build();
    }
}
