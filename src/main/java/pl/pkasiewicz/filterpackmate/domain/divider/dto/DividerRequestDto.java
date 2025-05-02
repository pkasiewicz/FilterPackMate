package pl.pkasiewicz.filterpackmate.domain.divider.dto;

import lombok.Builder;

@Builder
public record DividerRequestDto(
        String name
) {
}
