package pl.pkasiewicz.filterpackmate.domain.tray;

import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayRequestDto;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayResponseDto;

class TrayMapper {

    static Tray mapToEntity(TrayRequestDto dto) {
        return Tray.builder()
                .name(dto.name())
                .build();
    }

    static TrayResponseDto mapToDto(Tray entity) {
        return TrayResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .products(entity.getProducts())
                .build();
    }
}
