package pl.pkasiewicz.filterpackmate.domain.divider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.divider.exceptions.DividerNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DividerFacadeTest {

    @Mock
    private DividerRepository dividerRepository;

    @InjectMocks
    private DividerFacade dividerFacade;

    @Test
    void should_save_divider() {
        // given
        DividerRequestDto dividerRequestDto = new DividerRequestDto("EA");
        Divider returnedFromDb = new Divider(1L, "EA", null);
        when(dividerRepository.save(any(Divider.class))).thenReturn(returnedFromDb);

        // when
        DividerResponseDto response = dividerFacade.saveDivider(dividerRequestDto);

        // then
        ArgumentCaptor<Divider> captor = ArgumentCaptor.forClass(Divider.class);
        verify(dividerRepository).save(captor.capture());
        Divider savedDivider = captor.getValue();
        assertThat(savedDivider.getName()).isEqualTo("EA");
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("EA");
    }

    @Test
    void should_return_all_dividers() {
        // given
        List<Divider> dividers = List.of(
                new Divider(1L, "EA", null),
                new Divider(2L, "E-1", null),
                new Divider(3L, "EW", null)
        );
        when(dividerRepository.findAll()).thenReturn(dividers);

        // when
        List<DividerResponseDto> response = dividerFacade.getAllDividers();

        // then
        verify(dividerRepository).findAll();
        assertThat(response)
                .extracting(DividerResponseDto::name)
                .containsExactly("EA", "E-1", "EW");
        assertThat(response).hasSize(3);
    }

    @Test
    void should_return_divider_by_id() {
        // given
        Long id = 1L;
        Divider divider = new Divider(1L, "EA", null);
        when(dividerRepository.findById(id)).thenReturn(Optional.of(divider));
        DividerResponseDto dividerResponseDto = DividerMapper.mapToDto(divider);

        // when
        DividerResponseDto responseDto = dividerFacade.getDividerById(id);

        // then
        verify(dividerRepository).findById(id);
        assertThat(responseDto).isEqualTo(dividerResponseDto);
    }

    @Test
    void should_throw_exception_when_divider_with_given_id_does_not_exist() {
        // given
        Long id = 1L;
        when(dividerRepository.findById(id)).thenReturn(Optional.empty());

        // when && then
        assertThrows(DividerNotFoundException.class, () -> dividerFacade.getDividerById(id));
        verify(dividerRepository).findById(id);
    }

    @Test
    void should_throw_exception_when_divider_with_given_name_already_exist() {
        // given
        DividerRequestDto dividerRequestDto = new DividerRequestDto("EA");
        when(dividerRepository.save(any(Divider.class)))
                .thenThrow(new DuplicateKeyException("divider already exists"));

        // when && then
        assertThrows(DuplicateKeyException.class, () -> dividerFacade.saveDivider(dividerRequestDto));
        verify(dividerRepository).save(any(Divider.class));
    }

    @Test
    void should_return_correct_divider_response_for_each_id() {
        // given
        Divider divider1 = new Divider(1L, "EA", null);
        Divider divider2 = new Divider(2L, "E-1", null);
        when(dividerRepository.findById(1L)).thenReturn(Optional.of(divider1));
        when(dividerRepository.findById(2L)).thenReturn(Optional.of(divider2));

        // when
        DividerResponseDto response1 = dividerFacade.getDividerById(1L);
        DividerResponseDto response2 = dividerFacade.getDividerById(2L);

        // then
        assertThat(response1.name()).isEqualTo("EA");
        assertThat(response2.name()).isEqualTo("E-1");
    }

    @Test
    void should_return_empty_list_when_no_dividers_exist() {
        // given
        when(dividerRepository.findAll()).thenReturn(List.of());

        // when
        List<DividerResponseDto> response = dividerFacade.getAllDividers();

        // then
        verify(dividerRepository).findAll();
        assertThat(response).isEmpty();
    }
}