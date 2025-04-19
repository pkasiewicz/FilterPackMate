package pl.pkasiewicz.filterpackmate.domain.user.dto;

import lombok.Builder;

@Builder
public record UserRequestDto (
        String username,
        String password
) {
}
