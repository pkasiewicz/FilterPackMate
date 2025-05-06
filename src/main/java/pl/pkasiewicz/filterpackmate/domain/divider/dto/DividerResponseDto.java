package pl.pkasiewicz.filterpackmate.domain.divider.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductSummaryDto;

import java.util.List;

@Builder
public record DividerResponseDto(
        Long id,
        String name,
        List<ProductSummaryDto> products
) {
}
