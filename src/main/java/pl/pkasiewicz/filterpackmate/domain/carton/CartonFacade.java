package pl.pkasiewicz.filterpackmate.domain.carton;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonRequestDto;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonResponseDto;
import pl.pkasiewicz.filterpackmate.domain.carton.exceptions.CartonAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.carton.exceptions.CartonNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CartonFacade {

    public static final String CARTON_ALREADY_EXISTS = "Carton already exists";
    public static final String CARTON_NOT_FOUND = "Carton not found";
    private final CartonRepository cartonRepository;

    public CartonResponseDto saveCarton(CartonRequestDto cartonRequestDto) {
        if (cartonRepository.existsByName(cartonRequestDto.name())) {
            throw new CartonAlreadyExistsException(CARTON_ALREADY_EXISTS);
        }

        Carton savedCarton = cartonRepository.save(CartonMapper.mapToEntity(cartonRequestDto));
        return CartonMapper.mapToDto(savedCarton);
    }

    public List<CartonResponseDto> getAllCartons() {
        return cartonRepository.findAll()
                .stream()
                .map(CartonMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public CartonResponseDto getCartonById(Long id) {
        return cartonRepository.findById(id)
                .map(CartonMapper::mapToDto)
                .orElseThrow(() -> new CartonNotFoundException(CARTON_NOT_FOUND));
    }

    public Carton getCartonEntityById(Long id) {
        return cartonRepository.findById(id)
                .orElseThrow(() -> new CartonNotFoundException(CARTON_NOT_FOUND));
    }
}
