package pl.pkasiewicz.filterpackmate.domain.side.dto;

import lombok.Builder;

@Builder
public record SideDto(
        Long id,
        String name,
        int sideQty
) {
}
