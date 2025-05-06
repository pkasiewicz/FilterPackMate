package pl.pkasiewicz.filterpackmate.domain.divider.dto;

import lombok.Builder;

@Builder
public record DividerDto(
        Long id,
        String name,
        int quantityPerPallet
) {
}
