package pl.pkasiewicz.filterpackmate.domain.user.dto;

import lombok.Builder;

@Builder
public record UserDto(Long id, String username, String password) {
}
