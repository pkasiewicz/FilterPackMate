package pl.pkasiewicz.filterpackmate.domain.user.dto;

import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response DTO for user registration result")
@Builder
public record RegistrationResultDto(
        @Schema(description = "Unique ID of the registered user", example = "1")
        Long id,

        @Schema(description = "Username of the registered user", example = "john_doe")
        String username,

        @Schema(description = "Hashed password of the user", hidden = true)
        String password
) {
}
