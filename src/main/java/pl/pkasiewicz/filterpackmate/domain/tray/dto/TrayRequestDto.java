package pl.pkasiewicz.filterpackmate.domain.tray.dto;

import lombok.Builder;

@Builder
public record TrayRequestDto(
        String name
) {
}
