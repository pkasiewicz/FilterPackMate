package pl.pkasiewicz.filterpackmate.domain.carton;

import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonRequestDto;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.ProductMapper;

import java.util.List;
import java.util.Optional;

class CartonMapper {

    static Carton mapToEntity(CartonRequestDto dto) {
        return Carton.builder()
                .name(dto.name())
                .build();
    }

    static CartonResponseDto mapToDto(Carton entity) {
        return CartonResponseDto.builder()
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
