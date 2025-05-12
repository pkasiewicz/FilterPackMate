package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerDto;
import pl.pkasiewicz.filterpackmate.domain.product.Pallet;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideDto;

import java.util.List;

@Builder
public record ProductResponseDto (
        Long id,
        String name,
        Long cartonId,
        String cartonName,
        Long trayId,
        String trayName,
        int filtersPerCarton,
        int cartonsPerPallet,
        int filtersPerPallet,
        Pallet pallet,
        List<DividerDto> dividers,
        List<SideDto> sides,
        Long cornerId
) {
}
