package pl.pkasiewicz.filterpackmate.domain.corner;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.corner.exceptions.CornerAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.corner.exceptions.CornerNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CornerFacade {

    public static final String CORNER_NOT_FOUND = "Corner not found";
    public static final String CORNER_ALREADY_EXISTS = "Corner already exists";
    private final CornerRepository cornerRepository;

    public CornerResponseDto saveCorner(CornerRequestDto cornerRequestDto) {
        if (cornerRepository.existsByName(cornerRequestDto.name())) {
            throw new CornerAlreadyExistsException(CORNER_ALREADY_EXISTS);
        }

        Corner savedCorner = cornerRepository.save(CornerMapper.mapToEntity(cornerRequestDto));
        return CornerMapper.mapToCornerResponseDto(savedCorner);
    }

    public List<CornerResponseDto> getAllCorners() {
        return cornerRepository.findAll()
                .stream()
                .map(CornerMapper::mapToCornerResponseDto)
                .collect(Collectors.toList());
    }

    public CornerResponseDto getCornerById(Long id) {
        return cornerRepository.findById(id)
                .map(CornerMapper::mapToCornerResponseDto)
                .orElseThrow(() -> new CornerNotFoundException(CORNER_NOT_FOUND));
    }

    public Optional<Corner> getCornerEntityById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return cornerRepository.findById(id);
    }
}
