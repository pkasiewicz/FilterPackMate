package pl.pkasiewicz.filterpackmate.domain.tray;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayRequestDto;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayResponseDto;
import pl.pkasiewicz.filterpackmate.domain.tray.excteptions.TrayNotFoundException;

import java.util.List;

@AllArgsConstructor
public class TrayFacade {

    private final TrayRepository trayRepository;

    public TrayResponseDto saveTray(TrayRequestDto trayRequestDto) {
        Tray savedTray = trayRepository.save(TrayMapper.mapToEntity(trayRequestDto));
        return TrayMapper.mapToDto(savedTray);
    }

    public TrayResponseDto getTrayById(Long id) {
        return trayRepository.findById(id)
                .map(TrayMapper::mapToDto)
                .orElseThrow(() -> new TrayNotFoundException("tray not found"));
    }

    public List<TrayResponseDto> getAllTrays() {
        return trayRepository.findAll()
                .stream()
                .map(TrayMapper::mapToDto)
                .toList();
    }

    public Tray getTrayEntityById(Long id) {
        return trayRepository.findById(id)
                .orElseThrow(() -> new TrayNotFoundException("tray not found"));
    }
}
