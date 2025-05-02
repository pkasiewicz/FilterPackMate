package pl.pkasiewicz.filterpackmate.domain.side.dto;

import lombok.Builder;

@Builder
public record SideRequestDto(
        String name
) {
}
