package pl.pkasiewicz.filterpackmate.domain.divider;

import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerDto;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.ProductMapper;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductSummaryDto;

import java.util.List;
import java.util.Optional;

public class DividerMapper {

    static Divider mapToEntity(DividerRequestDto dto) {
        return Divider.builder()
                .name(dto.name())
                .quantityPerPallet(dto.quantityPerPallet())
                .build();
    }

    static DividerResponseDto mapToDividerResponseDto(Divider entity) {
        return DividerResponseDto.builder()
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

    public static DividerDto mapToDividerDto(Divider entity) {
        return DividerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .quantityPerPallet(entity.getQuantityPerPallet())
                .build();
    }
}
