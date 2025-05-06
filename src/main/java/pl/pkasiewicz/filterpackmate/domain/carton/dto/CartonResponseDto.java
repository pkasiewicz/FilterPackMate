package pl.pkasiewicz.filterpackmate.domain.carton.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductSummaryDto;

import java.util.List;

@Builder
public record CartonResponseDto(
        Long id,
        String name,
        List<ProductSummaryDto> products
) {
}
