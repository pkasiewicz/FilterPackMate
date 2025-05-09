package pl.pkasiewicz.filterpackmate.domain.tray;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayRequestDto;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayResponseDto;
import pl.pkasiewicz.filterpackmate.domain.tray.excteptions.TrayNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrayFacadeTest {

    @Mock
    private TrayRepository trayRepository;

    @InjectMocks
    private TrayFacade trayFacade;

    @Test
    void should_save_tray() {
        // given
        TrayRequestDto trayRequestDto = new TrayRequestDto("DE165");
        Tray returnedFromDb = new Tray(1L, "DE165", new ArrayList<>());

        when(trayRepository.save(any(Tray.class))).thenReturn(returnedFromDb);

        // when
        TrayResponseDto response = trayFacade.saveTray(trayRequestDto);

        // then
        ArgumentCaptor<Tray> captor = ArgumentCaptor.forClass(Tray.class);
        verify(trayRepository).save(captor.capture());
        Tray savedTray = captor.getValue();
        assertThat(savedTray.getName()).isEqualTo("DE165");

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("DE165");
    }

    @Test
    void should_return_all_trays() {
        // given
        List<Tray> trays = List.of(
                new Tray(1L, "DE165", new ArrayList<>()),
                new Tray(2L, "DE152",  new ArrayList<>()),
                new Tray(3L, "DE178",  new ArrayList<>())
        );

        // when
        when(trayRepository.findAll()).thenReturn(trays);
        List<TrayResponseDto> response = trayFacade.getAllTrays();

        // then
        verify(trayRepository).findAll();
        assertThat(response).extracting(TrayResponseDto::name).containsExactly("DE165", "DE152", "DE178");
        assertThat(response).hasSize(3);
    }

    @Test
    void should_return_tray_by_id() {
        // given
        Long id = 1L;
        Tray tray = new Tray(1L, "DE165",  new ArrayList<>());
        when(trayRepository.findById(id)).thenReturn(Optional.of(tray));
        TrayResponseDto trayResponseDto = TrayMapper.mapToDto(tray);

        // when
        TrayResponseDto responseDto = trayFacade.getTrayById(id);

        // then
        verify(trayRepository).findById(id);
        assertThat(responseDto).isEqualTo(trayResponseDto);
    }

    @Test
    void should_throw_exception_when_tray_with_given_id_does_not_exist() {
        // given
        Long id = 1L;
        when(trayRepository.findById(id)).thenReturn(Optional.empty());

        // when && then
        assertThrows(TrayNotFoundException.class, () -> trayFacade.getTrayById(id));
        verify(trayRepository).findById(id);
    }

    @Test
    void should_throw_exception_when_tray_with_given_name_already_exist() {
        // given
        TrayRequestDto trayRequestDto = new TrayRequestDto("DE165");
        when(trayRepository.save(any(Tray.class))).thenThrow(new DuplicateKeyException("tray already exists"));

        // when && then
        assertThrows(DuplicateKeyException.class, () -> trayFacade.saveTray(trayRequestDto));
        verify(trayRepository).save(any(Tray.class));
    }

    @Test
    void should_return_correct_tray_response_for_each_id() {
        // given
        Tray tray1 = new Tray(1L, "DE165",  new ArrayList<>());
        Tray tray2 = new Tray(2L, "DE152",  new ArrayList<>());

        when(trayRepository.findById(1L)).thenReturn(Optional.of(tray1));
        when(trayRepository.findById(2L)).thenReturn(Optional.of(tray2));

        // when
        TrayResponseDto response1 = trayFacade.getTrayById(1L);
        TrayResponseDto response2 = trayFacade.getTrayById(2L);

        // then
        assertThat(response1.name()).isEqualTo("DE165");
        assertThat(response2.name()).isEqualTo("DE152");
    }

    @Test
    void should_return_empty_list_when_no_trays_exist() {
        // given
        when(trayRepository.findAll()).thenReturn(List.of());

        // when
        List<TrayResponseDto> response = trayFacade.getAllTrays();

        // then
        verify(trayRepository).findAll();
        assertThat(response).isEmpty();
    }
}