package pl.pkasiewicz.filterpackmate.infrastructure.side;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pkasiewicz.filterpackmate.domain.side.SideFacade;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideRequestDto;
import pl.pkasiewicz.filterpackmate.domain.side.dto.SideResponseDto;

import java.util.List;

@RestController
@RequestMapping("/sides")
@AllArgsConstructor
@Tag(name = "Side Management", description = "Operations related to sides")
public class SideController {

    private final SideFacade sideFacade;

    @GetMapping("/{id}")
    @Operation(summary = "Get side by ID", description = "Returns a side by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found side"),
                    @ApiResponse(responseCode = "404", description = "Side not found")
            })
    public ResponseEntity<SideResponseDto> getSideById(@PathVariable Long id) {
        return ResponseEntity.ok(sideFacade.getSideById(id));
    }

    @GetMapping
    @Operation(summary = "Get all sides", description = "Returns all sides")
    public ResponseEntity<List<SideResponseDto>> getAllSides() {
        return ResponseEntity.ok(sideFacade.getAllSides());
    }

    @PostMapping
    @Operation(summary = "Add a new side", description = "Saves and returns the new side",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Side created")
            })
    public ResponseEntity<SideResponseDto> addSide(@RequestBody SideRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sideFacade.saveSide(dto));
    }
}