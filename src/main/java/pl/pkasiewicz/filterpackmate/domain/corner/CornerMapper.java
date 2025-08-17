package pl.pkasiewicz.filterpackmate.domain.corner;

import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerDto;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.ProductCorner;
import pl.pkasiewicz.filterpackmate.domain.product.ProductMapper;
import pl.pkasiewicz.filterpackmate.domain.side.Side;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideDto;

import java.util.List;
import java.util.Optional;

public class CornerMapper {

    public static Corner mapToEntity(CornerRequestDto dto) {
        return Corner.builder()
                .name(dto.name())
                .build();
    }

    public static CornerResponseDto mapToCornerResponseDto(Corner entity) {
        return CornerResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .products(
                        Optional.ofNullable(entity.getProductCorners())
                                .orElseGet(List::of)
                                .stream()
                                .map(ProductCorner::getProduct)
                                .map(ProductMapper::mapToProductSummaryDto)
                                .toList()
                )
                .build();
    }

    public static CornerDto mapToCornerDto(Corner entity) {
        return CornerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
