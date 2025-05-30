package pl.pkasiewicz.filterpackmate.domain.tray;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayRequestDto;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayResponseDto;
import pl.pkasiewicz.filterpackmate.domain.tray.excteptions.TrayAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.tray.excteptions.TrayNotFoundException;

import java.util.List;

@AllArgsConstructor
public class TrayFacade {

    public static final String TRAY_ALREADY_EXISTS = "Tray already exists";
    public static final String TRAY_NOT_FOUND = "Tray not found";
    private final TrayRepository trayRepository;

    public TrayResponseDto saveTray(TrayRequestDto trayRequestDto) {
        if (trayRepository.existsByName(trayRequestDto.name())) {
            throw new TrayAlreadyExistsException(TRAY_ALREADY_EXISTS);
        }

        Tray savedTray = trayRepository.save(TrayMapper.mapToEntity(trayRequestDto));
        return TrayMapper.mapToDto(savedTray);
    }

    public TrayResponseDto getTrayById(Long id) {
        return trayRepository.findById(id)
                .map(TrayMapper::mapToDto)
                .orElseThrow(() -> new TrayNotFoundException(TRAY_NOT_FOUND));
    }

    public List<TrayResponseDto> getAllTrays() {
        return trayRepository.findAll()
                .stream()
                .map(TrayMapper::mapToDto)
                .toList();
    }

    public Tray getTrayEntityById(Long id) {
        return trayRepository.findById(id)
                .orElseThrow(() -> new TrayNotFoundException(TRAY_NOT_FOUND));
    }
}
