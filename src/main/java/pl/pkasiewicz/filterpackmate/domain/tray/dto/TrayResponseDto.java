package pl.pkasiewicz.filterpackmate.domain.tray.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.product.Product;

import java.util.List;

@Builder
public record TrayResponseDto(
        Long id,
        String name,
        List<Product> products
) {
}
