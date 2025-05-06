package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;

@Builder
public record ProductSummaryDto(
        Long id,
        String name
) {}
