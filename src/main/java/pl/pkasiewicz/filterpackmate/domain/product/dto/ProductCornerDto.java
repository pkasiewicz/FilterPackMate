package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerDto;

@Builder
public record ProductCornerDto(
        CornerDto corner,
        boolean isLotted
) {
}