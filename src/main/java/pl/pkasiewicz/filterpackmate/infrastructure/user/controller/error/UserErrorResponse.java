package pl.pkasiewicz.filterpackmate.infrastructure.user.controller.error;

import org.springframework.http.HttpStatus;

public record UserErrorResponse(
        String message,
        HttpStatus status
) {
}
