package pl.pkasiewicz.filterpackmate.domain.divider;

import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;

class DividerMapper {

    static Divider mapToEntity(DividerRequestDto dto) {
        return Divider.builder()
                .name(dto.name())
                .build();
    }

    static DividerResponseDto mapToDto(Divider entity) {
        return DividerResponseDto.builder()
                .id(entity.id)
                .name(entity.name)
                .products(entity.products)
                .build();
    }
}
