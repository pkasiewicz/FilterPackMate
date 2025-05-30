package pl.pkasiewicz.filterpackmate.infrastructure.error;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String message,
        HttpStatus status
) {
}