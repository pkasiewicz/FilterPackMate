package pl.pkasiewicz.filterpackmate.infrastructure.divider.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerFacade;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerRequestDto;
import pl.pkasiewicz.filterpackmate.domain.divider.dto.DividerResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/dividers")
@AllArgsConstructor
@Tag(name = "Divider Management", description = "Operations related to dividers")
public class DividerController {

    private final DividerFacade dividerFacade;

    @GetMapping("/{id}")
    @Operation(summary = "Get divider by ID", description = "Returns a divider by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found divider"),
                    @ApiResponse(responseCode = "404", description = "Divider not found")
            })
    public ResponseEntity<DividerResponseDto> getDividerById(@PathVariable Long id) {
        return ResponseEntity.ok(dividerFacade.getDividerById(id));
    }

    @GetMapping
    @Operation(summary = "Get all dividers", description = "Returns all dividers")
    public ResponseEntity<List<DividerResponseDto>> getAllDividers() {
        return ResponseEntity.ok(dividerFacade.getAllDividers());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new divider", description = "Saves and returns the new divider",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Divider created")
            })
    public ResponseEntity<DividerResponseDto> addDivider(@RequestBody DividerRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dividerFacade.saveDivider(dto));
    }
}