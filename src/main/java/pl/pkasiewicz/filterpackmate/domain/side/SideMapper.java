package pl.pkasiewicz.filterpackmate.domain.side;

import pl.pkasiewicz.filterpackmate.domain.product.ProductMapper;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideRequestDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;

import java.util.List;
import java.util.Optional;

public class SideMapper {

    public static SideResponseDto mapSideResponseDto(Side entity) {
        return SideResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .products(
                        Optional.ofNullable(entity.getProducts())
                                .orElseGet(List::of)
                                .stream()
                                .map(ProductMapper::mapToProductSummaryDto)
                                .toList()
                )
                .build();
    }

    public static Side mapToEntity(SideRequestDto dto) {
        return Side.builder()
                .name(dto.name())
                .build();
    }

    public static SideDto mapToSideDto(Side entity) {
        return SideDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
