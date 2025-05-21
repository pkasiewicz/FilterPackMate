package pl.pkasiewicz.filterpackmate.infrastructure.tray;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pkasiewicz.filterpackmate.domain.tray.TrayFacade;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayRequestDto;
import pl.pkasiewicz.filterpackmate.domain.tray.dto.TrayResponseDto;

import java.util.List;

@RestController
@RequestMapping("/trays")
@AllArgsConstructor
@Tag(name = "Tray Management", description = "Operations related to trays")
public class TrayController {

    private final TrayFacade trayFacade;

    @GetMapping("/{id}")
    @Operation(summary = "Get tray by ID", description = "Returns a tray by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found tray"),
                    @ApiResponse(responseCode = "404", description = "Tray not found")
            })
    public ResponseEntity<TrayResponseDto> getTrayById(@PathVariable Long id) {
        return ResponseEntity.ok(trayFacade.getTrayById(id));
    }

    @GetMapping
    @Operation(summary = "Get all trays", description = "Returns all trays")
    public ResponseEntity<List<TrayResponseDto>> getAllTrays() {
        return ResponseEntity.ok(trayFacade.getAllTrays());
    }

    @PostMapping
    @Operation(summary = "Add a new tray", description = "Saves and returns the new tray",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tray created")
            })
    public ResponseEntity<TrayResponseDto> addTray(@RequestBody TrayRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trayFacade.saveTray(dto));
    }
}