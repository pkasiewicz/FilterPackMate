package pl.pkasiewicz.filterpackmate.domain.corner.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductSummaryDto;

import java.util.List;

@Builder
public record CornerResponseDto(
        Long id,
        String name,
        List<ProductSummaryDto> products
) {
}
