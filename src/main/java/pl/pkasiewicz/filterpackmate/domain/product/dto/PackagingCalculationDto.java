package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerDto;

import java.util.Set;

@Builder
public record PackagingCalculationDto(
        Long productId,
        String productName,
        Integer productQty,
        String cartonName,
        Integer cartonQty,
        String trayName,
        Integer trayQty,
        Set<DividerDto> dividers,
        Set<ProductSideDto> sides,
        Set<ProductCornerDto> corners,
        String palletType,
        Integer palletQty
) {
}
