package pl.pkasiewicz.filterpackmate.infrastructure.carton.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pkasiewicz.filterpackmate.domain.carton.CartonFacade;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonRequestDto;
import pl.pkasiewicz.filterpackmate.domain.carton.dto.CartonResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/cartons")
@AllArgsConstructor
@Tag(name = "Carton Management", description = "Operations related to cartons")
public class CartonController {

    private final CartonFacade cartonFacade;

    @GetMapping("/{id}")
    @Operation(summary = "Get carton by ID", description = "Returns a single carton by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found carton"),
                    @ApiResponse(responseCode = "404", description = "Carton not found")
            })
    public ResponseEntity<CartonResponseDto> getCartonById(@PathVariable Long id) {
        return ResponseEntity.ok(cartonFacade.getCartonById(id));
    }

    @GetMapping
    @Operation(summary = "Get all cartons", description = "Returns a list of all cartons")
    public ResponseEntity<List<CartonResponseDto>> getAllCartons() {
        return ResponseEntity.ok(cartonFacade.getAllCartons());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new carton", description = "Creates and returns the saved carton",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Carton created successfully")
            })
    public ResponseEntity<CartonResponseDto> addCarton(@RequestBody CartonRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartonFacade.saveCarton(dto));
    }
}