package pl.pkasiewicz.filterpackmate.domain.side.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.product.Product;

import java.util.List;

@Builder
public record SideResponseDto(
        Long id,
        String name,
        List<Product> products
) {
}
