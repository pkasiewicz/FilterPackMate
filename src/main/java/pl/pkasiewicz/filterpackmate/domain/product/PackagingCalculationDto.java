package pl.pkasiewicz.filterpackmate.domain.product;

import lombok.Builder;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideDto;

import java.util.Set;

@Builder
public record PackagingCalculationDto(
        Long productId,
        String productName,
        int productQty,
        String cartonName,
        int cartonQty,
        String trayName,
        int trayQty,
        Set<DividerDto> dividers,
        Set<SideDto> sides,
        Integer cornerQty
) {}
