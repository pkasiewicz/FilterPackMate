package pl.pkasiewicz.filterpackmate.domain.divider;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.divider.exceptions.DividerNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DividerFacade {

    private final DividerRepository dividerRepository;

    public DividerResponseDto saveDivider(DividerRequestDto dividerRequestDto) {
        Divider savedDivider = dividerRepository.save(DividerMapper.mapToEntity(dividerRequestDto));
        return DividerMapper.mapToDividerResponseDto(savedDivider);
    }

    public List<DividerResponseDto> getAllDividers() {
        return dividerRepository.findAll()
                .stream()
                .map(DividerMapper::mapToDividerResponseDto)
                .collect(Collectors.toList());
    }

    public DividerResponseDto getDividerById(Long id) {
        return dividerRepository.findById(id)
                .map(DividerMapper::mapToDividerResponseDto)
                .orElseThrow(() -> new DividerNotFoundException("divider not found"));
    }

    public Divider getDividerEntityById(Long id) {
        return dividerRepository.findById(id)
                .orElseThrow(() -> new DividerNotFoundException("divider not found"));
    }
}
