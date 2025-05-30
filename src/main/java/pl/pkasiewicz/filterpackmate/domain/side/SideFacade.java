package pl.pkasiewicz.filterpackmate.domain.side;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideRequestDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;
import pl.pkasiewicz.filterpackmate.domain.side.exceptions.SideAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.side.exceptions.SideNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SideFacade {

    public static final String SIDE_ALREADY_EXISTS = "Tray already exists";
    public static final String SIDE_NOT_FOUND = "Side not found";
    private final SideRepository sideRepository;

    public SideResponseDto saveSide(SideRequestDto sideRequestDto) {
        if (sideRepository.existsByName(sideRequestDto.name())) {
            throw new SideAlreadyExistsException(SIDE_ALREADY_EXISTS);
        }

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
                .orElseThrow(() -> new SideNotFoundException(SIDE_NOT_FOUND));
    }

    public Optional<Side> getSideEntityById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return sideRepository.findById(id);
    }
}
