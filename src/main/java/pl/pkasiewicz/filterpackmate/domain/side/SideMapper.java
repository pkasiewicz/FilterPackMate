package pl.pkasiewicz.filterpackmate.domain.side;

import pl.pkasiewicz.filterpackmate.domain.side.dto.SideRequestDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;

class SideMapper {

    public static SideResponseDto mapToDto(Side side) {
        return SideResponseDto.builder()
                .id(side.getId())
                .name(side.getName())
                .products(side.getProducts())
                .build();
    }

    public static Side mapToEntity(SideRequestDto sideRequestDto) {
        return Side.builder()
                .name(sideRequestDto.name())
                .build();
    }
}
