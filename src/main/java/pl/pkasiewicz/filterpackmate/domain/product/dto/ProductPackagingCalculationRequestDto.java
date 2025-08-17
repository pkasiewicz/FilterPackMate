package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;

@Builder
public record ProductPackagingCalculationRequestDto(
        Long productId,
        int productQty,
        boolean isLotted
) {
}
