package pl.pkasiewicz.filterpackmate.domain.side;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideRequestDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;
import pl.pkasiewicz.filterpackmate.domain.side.exceptions.SideNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SideFacadeTest {

    @Mock
    private SideRepository sideRepository;

    @InjectMocks
    private SideFacade sideFacade;

    @Test
    void should_save_side() {
        // given
        SideRequestDto sideRequestDto = new SideRequestDto("BJ900A");
        Side returnedFromDb = new Side(1L, "BJ900A", null);
        when(sideRepository.save(any(Side.class))).thenReturn(returnedFromDb);

        // when
        SideResponseDto response = sideFacade.saveSide(sideRequestDto);

        // then
        ArgumentCaptor<Side> captor = ArgumentCaptor.forClass(Side.class);
        verify(sideRepository).save(captor.capture());
        Side savedSide = captor.getValue();
        assertThat(savedSide.getName()).isEqualTo("BJ900A");
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("BJ900A");
    }

    @Test
    void should_return_all_sides() {
        // given
        List<Side> sides = List.of(
                new Side(1L, "BJ900A", null),
                new Side(2L, "BE900B", null),
                new Side(3L, "BE770A", null)
        );
        when(sideRepository.findAll()).thenReturn(sides);

        // when
        List<SideResponseDto> response = sideFacade.getAllSides();

        // then
        verify(sideRepository).findAll();
        assertThat(response)
                .extracting(SideResponseDto::name)
                .containsExactly("BJ900A", "BE900B", "BE770A");
        assertThat(response).hasSize(3);
    }

    @Test
    void should_return_side_by_id() {
        // given
        Long id = 1L;
        Side side = new Side(1L, "BJ900A", null);
        when(sideRepository.findById(id)).thenReturn(Optional.of(side));
        SideResponseDto sideResponseDto = SideMapper.mapToDto(side);

        // when
        SideResponseDto responseDto = sideFacade.getSideById(id);

        // then
        verify(sideRepository).findById(id);
        assertThat(responseDto).isEqualTo(sideResponseDto);
    }

    @Test
    void should_throw_exception_when_side_with_given_id_does_not_exist() {
        // given
        Long id = 1L;
        when(sideRepository.findById(id)).thenReturn(Optional.empty());

        // when && then
        assertThrows(SideNotFoundException.class, () -> sideFacade.getSideById(id));
        verify(sideRepository).findById(id);
    }

    @Test
    void should_throw_exception_when_side_with_given_name_already_exist() {
        // given
        SideRequestDto sideRequestDto = new SideRequestDto("BJ900A");
        when(sideRepository.save(any(Side.class))).thenThrow(new DuplicateKeyException("side already exists"));

        // when && then
        assertThrows(DuplicateKeyException.class, () -> sideFacade.saveSide(sideRequestDto));
        verify(sideRepository).save(any(Side.class));
    }

    @Test
    void should_return_correct_side_response_for_each_id() {
        // given
        Side side1 = new Side(1L, "BJ900A", null);
        Side side2 = new Side(2L, "BE900B", null);
        when(sideRepository.findById(1L)).thenReturn(Optional.of(side1));
        when(sideRepository.findById(2L)).thenReturn(Optional.of(side2));

        // when
        SideResponseDto response1 = sideFacade.getSideById(1L);
        SideResponseDto response2 = sideFacade.getSideById(2L);

        // then
        assertThat(response1.name()).isEqualTo("BJ900A");
        assertThat(response2.name()).isEqualTo("BE900B");
    }

    @Test
    void should_return_empty_list_when_no_sides_exist() {
        // given
        when(sideRepository.findAll()).thenReturn(List.of());

        // when
        List<SideResponseDto> response = sideFacade.getAllSides();

        // then
        verify(sideRepository).findAll();
        assertThat(response).isEmpty();
    }
}