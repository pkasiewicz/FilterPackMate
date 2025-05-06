package pl.pkasiewicz.filterpackmate.domain.product;

import pl.pkasiewicz.filterpackmate.domain.divider.DividerMapper;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;
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
                .pallet(entity.getPallet())
                .dividers(
                        Optional.ofNullable(entity.getDividers())
                                .orElseGet(List::of)
                                .stream()
                                .map(DividerMapper::mapToDividerDto)
                                .toList()
                )
                .sides(
                        Optional.ofNullable(entity.getSides())
                                .orElseGet(List::of)
                                .stream()
                                .map(SideMapper::mapToSideDto)
                                .toList())
                .build();
    }

    public static ProductSummaryDto mapToProductSummaryDto(Product entity) {
        return ProductSummaryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
