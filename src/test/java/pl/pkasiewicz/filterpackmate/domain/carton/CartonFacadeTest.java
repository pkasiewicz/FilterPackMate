package pl.pkasiewicz.filterpackmate.domain.carton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonRequestDto;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonResponseDto;
import pl.pkasiewicz.filterpackmate.domain.carton.exceptions.CartonNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartonFacadeTest {

    @Mock
    private CartonRepository cartonRepository;

    @InjectMocks
    private CartonFacade cartonFacade;

    @Test
    void should_save_carton() {
        // given
        CartonRequestDto cartonRequestDto = new CartonRequestDto("PCA-12");
        Carton returnedFromDb = new Carton(1L, "PCA-12", 1, new ArrayList<>());
        when(cartonRepository.save(any(Carton.class))).thenReturn(returnedFromDb);

        // when
        CartonResponseDto response = cartonFacade.saveCarton(cartonRequestDto);

        // then
        ArgumentCaptor<Carton> captor = ArgumentCaptor.forClass(Carton.class);
        verify(cartonRepository).save(captor.capture());
        Carton savedCarton = captor.getValue();
        assertThat(savedCarton.getName()).isEqualTo("PCA-12");
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("PCA-12");
    }

    @Test
    void should_return_all_cartons() {
        // given
        List<Carton> cartons = List.of(
                new Carton(1L, "PCA-12", 1, new ArrayList<>()),
                new Carton(2L, "PCA-13", 1, new ArrayList<>()),
                new Carton(3L, "PCA-14", 1, new ArrayList<>())
        );
        when(cartonRepository.findAll()).thenReturn(cartons);

        // when
        List<CartonResponseDto> response = cartonFacade.getAllCartons();

        // then
        verify(cartonRepository).findAll();
        assertThat(response)
                .extracting(CartonResponseDto::name)
                .containsExactly("PCA-12", "PCA-13", "PCA-14");
        assertThat(response).hasSize(3);
    }

    @Test
    void should_return_carton_by_id() {
        // given
        Long id = 1L;
        Carton carton = new Carton(1L, "PCA-12", 1, new ArrayList<>());
        when(cartonRepository.findById(id)).thenReturn(Optional.of(carton));
        CartonResponseDto cartonResponseDto = CartonMapper.mapToDto(carton);

        // when
        CartonResponseDto responseDto = cartonFacade.getCartonById(id);

        // then
        verify(cartonRepository).findById(id);
        assertThat(responseDto).isEqualTo(cartonResponseDto);
    }

    @Test
    void should_throw_exception_when_carton_with_given_id_does_not_exist() {
        // given
        Long id = 1L;
        when(cartonRepository.findById(id)).thenReturn(Optional.empty());

        // when && then
        assertThrows(CartonNotFoundException.class, () -> cartonFacade.getCartonById(id));
        verify(cartonRepository).findById(id);
    }

    @Test
    void should_throw_exception_when_carton_with_given_name_already_exist() {
        // given
        CartonRequestDto cartonRequestDto = new CartonRequestDto("PCA-12");
        when(cartonRepository.save(any(Carton.class)))
                .thenThrow(new DuplicateKeyException("carton already exists"));

        // when && then
        assertThrows(DuplicateKeyException.class, () -> cartonFacade.saveCarton(cartonRequestDto));
        verify(cartonRepository).save(any(Carton.class));
    }

    @Test
    void should_return_correct_carton_response_for_each_id() {
        // given
        Carton carton1 = new Carton(1L, "PCA-12", 1, new ArrayList<>());
        Carton carton2 = new Carton(2L, "PCA-13", 1, new ArrayList<>());
        when(cartonRepository.findById(1L)).thenReturn(Optional.of(carton1));
        when(cartonRepository.findById(2L)).thenReturn(Optional.of(carton2));

        // when
        CartonResponseDto response1 = cartonFacade.getCartonById(1L);
        CartonResponseDto response2 = cartonFacade.getCartonById(2L);

        // then
        assertThat(response1.name()).isEqualTo("PCA-12");
        assertThat(response2.name()).isEqualTo("PCA-13");
    }

    @Test
    void should_return_empty_list_when_no_cartons_exist() {
        // given
        when(cartonRepository.findAll()).thenReturn(List.of());

        // when
        List<CartonResponseDto> response = cartonFacade.getAllCartons();

        // then
        verify(cartonRepository).findAll();
        assertThat(response).isEmpty();
    }
}