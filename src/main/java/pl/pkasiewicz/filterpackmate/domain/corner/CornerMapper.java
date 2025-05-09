package pl.pkasiewicz.filterpackmate.domain.corner;

import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.ProductMapper;

import java.util.List;
import java.util.Optional;

class CornerMapper {
    static Corner mapToEntity(CornerRequestDto dto) {
        return Corner.builder()
                .name(dto.name())
                .build();
    }

    static CornerResponseDto mapToCornerResponseDto(Corner entity) {
        return CornerResponseDto.builder()
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


}
