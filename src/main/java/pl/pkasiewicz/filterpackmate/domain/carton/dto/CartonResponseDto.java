package pl.pkasiewicz.filterpackmate.domain.carton.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.product.Product;

import java.util.List;

@Builder
public record CartonResponseDto(
        Long id,
        String name,
        List<Product> products
) {
}
