package pl.pkasiewicz.filterpackmate.domain.side.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductSummaryDto;

import java.util.List;

@Builder
public record SideResponseDto(
        Long id,
        String name,
        List<ProductSummaryDto> products
) {
}
