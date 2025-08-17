package pl.pkasiewicz.filterpackmate.infrastructure.corner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pkasiewicz.filterpackmate.domain.corner.CornerFacade;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.corner.dto.CornerResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/corners")
@AllArgsConstructor
@Tag(name = "Corner Management", description = "Operations related to corners")
public class CornerController {

    private final CornerFacade cornerFacade;

    @GetMapping("/{id}")
    @Operation(summary = "Get corner by ID", description = "Returns a corner by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found corner"),
                    @ApiResponse(responseCode = "404", description = "Corner not found")
            })
    public ResponseEntity<CornerResponseDto> getCornerById(@PathVariable Long id) {
        return ResponseEntity.ok(cornerFacade.getCornerById(id));
    }

    @GetMapping
    @Operation(summary = "Get all corners", description = "Returns all corners")
    public ResponseEntity<List<CornerResponseDto>> getAllCorners() {
        return ResponseEntity.ok(cornerFacade.getAllCorners());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new corner", description = "Saves and returns the new corner",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Corner created")
            })
    public ResponseEntity<CornerResponseDto> addCorner(@RequestBody CornerRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cornerFacade.saveCorner(dto));
    }
}