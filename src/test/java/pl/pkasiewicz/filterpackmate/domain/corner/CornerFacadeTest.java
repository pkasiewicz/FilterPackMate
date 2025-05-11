package pl.pkasiewicz.filterpackmate.domain.corner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerResponseDto;
import pl.pkasiewicz.filterpackmate.domain.corner.exceptions.CornerNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CornerFacadeTest {

    @Mock
    private CornerRepository cornerRepository;

    @InjectMocks
    private CornerFacade cornerFacade;

    @Test
    void should_save_corner() {
        // given
        CornerRequestDto cornerRequestDto = new CornerRequestDto("CP950");
        Corner returnedFromDb = new Corner(1L, "CP950", new ArrayList<>());
        when(cornerRepository.save(any(Corner.class))).thenReturn(returnedFromDb);

        // when
        CornerResponseDto response = cornerFacade.saveCorner(cornerRequestDto);

        // then
        ArgumentCaptor<Corner> captor = ArgumentCaptor.forClass(Corner.class);
        verify(cornerRepository).save(captor.capture());
        Corner savedCorner = captor.getValue();
        assertThat(savedCorner.getName()).isEqualTo("CP950");
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("CP950");
    }

    @Test
    void should_return_all_corners() {
        // given
        List<Corner> corners = List.of(
                new Corner(1L, "CP950",new ArrayList<>()),
                new Corner(2L, "CP900", new ArrayList<>()),
                new Corner(3L, "CP850", new ArrayList<>())
        );
        when(cornerRepository.findAll()).thenReturn(corners);

        // when
        List<CornerResponseDto> response = cornerFacade.getAllCorners();

        // then
        verify(cornerRepository).findAll();
        assertThat(response)
                .extracting(CornerResponseDto::name)
                .containsExactly("CP950", "CP900", "CP850");
        assertThat(response).hasSize(3);
    }

    @Test
    void should_return_corner_by_id() {
        // given
        Long id = 1L;
        Corner corner = new Corner(1L, "CP950", new ArrayList<>());
        when(cornerRepository.findById(id)).thenReturn(Optional.of(corner));
        CornerResponseDto cornerResponseDto = CornerMapper.mapToCornerResponseDto(corner);

        // when
        CornerResponseDto responseDto = cornerFacade.getCornerById(id);

        // then
        verify(cornerRepository).findById(id);
        assertThat(responseDto).isEqualTo(cornerResponseDto);
    }

    @Test
    void should_throw_exception_when_corner_with_given_id_does_not_exist() {
        // given
        Long id = 1L;
        when(cornerRepository.findById(id)).thenReturn(Optional.empty());

        // when && then
        assertThrows(CornerNotFoundException.class, () -> cornerFacade.getCornerById(id));
        verify(cornerRepository).findById(id);
    }

    @Test
    void should_throw_exception_when_corner_with_given_name_already_exist() {
        // given
        CornerRequestDto cornerRequestDto = new CornerRequestDto("CP950");
        when(cornerRepository.save(any(Corner.class)))
                .thenThrow(new DuplicateKeyException("corner already exists"));

        // when && then
        assertThrows(DuplicateKeyException.class, () -> cornerFacade.saveCorner(cornerRequestDto));
        verify(cornerRepository).save(any(Corner.class));
    }

    @Test
    void should_return_correct_corner_response_for_each_id() {
        // given
        Corner corner1 = new Corner(1L, "CP950", new ArrayList<>());
        Corner corner2 = new Corner(2L, "CP850", new ArrayList<>());
        when(cornerRepository.findById(1L)).thenReturn(Optional.of(corner1));
        when(cornerRepository.findById(2L)).thenReturn(Optional.of(corner2));

        // when
        CornerResponseDto response1 = cornerFacade.getCornerById(1L);
        CornerResponseDto response2 = cornerFacade.getCornerById(2L);

        // then
        assertThat(response1.name()).isEqualTo("CP950");
        assertThat(response2.name()).isEqualTo("CP850");
    }

    @Test
    void should_return_empty_list_when_no_corners_exist() {
        // given
        when(cornerRepository.findAll()).thenReturn(List.of());

        // when
        List<CornerResponseDto> response = cornerFacade.getAllCorners();

        // then
        verify(cornerRepository).findAll();
        assertThat(response).isEmpty();
    }
}