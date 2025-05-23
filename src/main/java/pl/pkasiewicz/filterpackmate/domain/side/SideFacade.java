package pl.pkasiewicz.filterpackmate.domain.side;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideRequestDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;
import pl.pkasiewicz.filterpackmate.domain.side.exceptions.SideNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SideFacade {

    private final SideRepository sideRepository;

    public SideResponseDto saveSide(SideRequestDto sideRequestDto) {
        Side savedSide = sideRepository.save(SideMapper.mapToEntity(sideRequestDto));
        return SideMapper.mapToSideResponseDto(savedSide);
    }

    public List<SideResponseDto> getAllSides() {
        return sideRepository.findAll()
                .stream()
                .map(SideMapper::mapToSideResponseDto)
                .collect(Collectors.toList());
    }

    public SideResponseDto getSideById(Long id) {
        return sideRepository.findById(id)
                .map(SideMapper::mapToSideResponseDto)
                .orElseThrow(() -> new SideNotFoundException("side not found"));
    }

    public Side getSideEntityById(Long id) {
        return sideRepository.findById(id)
                .orElseThrow(() -> new SideNotFoundException("side not found"));
    }
}
