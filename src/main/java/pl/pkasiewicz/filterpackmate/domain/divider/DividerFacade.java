package pl.pkasiewicz.filterpackmate.domain.divider;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.divider.exceptions.DividerAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.divider.exceptions.DividerNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.tray.excteptions.TrayAlreadyExistsException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DividerFacade {

    public static final String DIVIDER_ALREADY_EXISTS = "Divider already exists";
    public static final String DIVIDER_NOT_FOUND = "Divider not found";
    private final DividerRepository dividerRepository;

    public DividerResponseDto saveDivider(DividerRequestDto dividerRequestDto) {
        if (dividerRepository.existsByName(dividerRequestDto.name())) {
            throw new DividerAlreadyExistsException(DIVIDER_ALREADY_EXISTS);
        }

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
                .orElseThrow(() -> new DividerNotFoundException(DIVIDER_NOT_FOUND));
    }

    public Optional<Divider> getDividerEntityById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return dividerRepository.findById(id);
    }
}
