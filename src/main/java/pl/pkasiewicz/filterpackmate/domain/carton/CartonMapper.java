package pl.pkasiewicz.filterpackmate.domain.carton;

import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonRequestDto;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonResponseDto;

class CartonMapper {

    static Carton mapToEntity(CartonRequestDto dto) {
        return Carton.builder()
                .name(dto.name())
                .build();
    }

    static CartonResponseDto mapToDto(Carton carton) {
        return CartonResponseDto.builder()
                .id(carton.getId())
                .name(carton.getName())
                .products(carton.getProducts())
                .build();
    }
}
