package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideDto;

@Builder
public record ProductSideDto(
        SideDto side,
        boolean isLotted
) {
}
