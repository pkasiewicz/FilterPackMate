package pl.pkasiewicz.filterpackmate.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Request DTO for user registration")
public record RegisterUserDto(
        @Schema(description = "Username of the new user") String username,
        @Schema(description = "Plain text password (will be hashed)") String password) {}
