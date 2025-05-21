package pl.pkasiewicz.filterpackmate.infrastructure.user.controller.dto;

public record TokenRequestDto(
        String username,
        String password
) {
}
