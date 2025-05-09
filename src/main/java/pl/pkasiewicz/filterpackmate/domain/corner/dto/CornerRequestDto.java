package pl.pkasiewicz.filterpackmate.domain.corner.dto;

import lombok.Builder;

@Builder
public record CornerRequestDto(
        String name
) {
}
