package pl.pkasiewicz.filterpackmate.domain.corner;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.corner.exceptions.CornerNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CornerFacade {

    private final CornerRepository cornerRepository;

    public CornerResponseDto saveCorner(CornerRequestDto cornerRequestDto) {
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
                .orElseThrow(() -> new CornerNotFoundException("corner not found"));
    }

    public Optional<Corner> getCornerEntityById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return cornerRepository.findById(id);
    }
}
