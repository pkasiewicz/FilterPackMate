package pl.pkasiewicz.filterpackmate.domain.carton.dto;

import lombok.Builder;

@Builder
public record CartonRequestDto(
        String name
) {
}
