package pl.pkasiewicz.filterpackmate.domain.divider.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.product.Product;

import java.util.List;

@Builder
public record DividerResponseDto(
        Long id,
        String name,
        List<Product> products
) {
}
