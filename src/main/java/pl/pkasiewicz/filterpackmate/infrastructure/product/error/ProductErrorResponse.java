package pl.pkasiewicz.filterpackmate.infrastructure.product.error;

import org.springframework.http.HttpStatus;

public record ProductErrorResponse(
        String message,
        HttpStatus status
) {
}
