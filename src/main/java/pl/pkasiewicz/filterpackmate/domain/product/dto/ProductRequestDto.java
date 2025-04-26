package pl.pkasiewicz.filterpackmate.domain.product.dto;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.carton.Carton;
import pl.pkasiewicz.filterpackmate.domain.divider.Divider;
import pl.pkasiewicz.filterpackmate.domain.product.Pallet;
import pl.pkasiewicz.filterpackmate.domain.side.Side;
import pl.pkasiewicz.filterpackmate.domain.tray.Tray;

import java.util.List;

@Builder
public record ProductRequestDto(
        Carton carton,
        Tray tray,
        Pallet pallet,
        List<Divider> dividers,
        List<Side> sides
) {
}
