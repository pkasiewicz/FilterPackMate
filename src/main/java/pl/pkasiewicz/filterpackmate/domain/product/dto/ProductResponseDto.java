package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerDto;

import java.util.List;

@Builder
public record ProductResponseDto (
        Long id,
        String name,
        Long cartonId,
        String cartonName,
        Long trayId,
        String trayName,
        Integer filtersPerCarton,
        Integer cartonsPerPallet,
        Integer filtersPerPallet,
        String palletType,
        List<DividerDto> dividers,
        List<ProductSideDto> productSides,
        List<ProductCornerDto> productCorners
) {
}
