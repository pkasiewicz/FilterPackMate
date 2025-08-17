package pl.pkasiewicz.filterpackmate.domain.corner.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record CornerDto(
        Long id,
        String name,
        int cornerQty
) {
}
