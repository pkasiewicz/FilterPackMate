package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductRequestDto(
        String name,
        Integer filtersPerCarton,
        Integer cartonsPerPallet,
        Integer filtersPerPallet,
        Long cartonId,
        Long trayId,
        String palletType,
        List<Long> dividerIds,
        List<Long> sideIds,
        List<Long> lottedSideIds,
        Long cornerId,
        Long lottedCornerId
) {
}
