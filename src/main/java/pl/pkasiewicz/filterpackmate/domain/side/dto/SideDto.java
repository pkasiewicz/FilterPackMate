package pl.pkasiewicz.filterpackmate.domain.side.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record SideDto(
        Long id,
        String name,
        int sideQty
) {
}
