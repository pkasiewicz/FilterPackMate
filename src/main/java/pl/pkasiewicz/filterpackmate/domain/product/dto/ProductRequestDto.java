package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.product.Pallet;

import java.util.List;

@Builder
public record ProductRequestDto(
        String name,
        int filtersPerCarton,
        int cartonsPerPallet,
        int filtersPerPallet,
        Long cartonId,
        Long trayId,
        Pallet pallet,
        List<Long> dividerIds,
        List<Long> sideIds,
        Long cornerId
) {
}
