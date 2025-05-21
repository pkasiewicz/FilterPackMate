package pl.pkasiewicz.filterpackmate.domain.user;

import pl.pkasiewicz.filterpackmate.domain.user.dto.RegisterUserDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.RegistrationResultDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.UserDto;

class UserMapper {

     static User mapToEntity(RegisterUserDto registerUserDto) {
        return User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .build();
    }

    static RegistrationResultDto mapToRegistrationResultDto(User entity) {
         return RegistrationResultDto.builder()
                 .id(entity.getId())
                 .username(entity.getUsername())
                 .password(entity.getPassword())
                 .build();
    }

    static UserDto mapToUserDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }
}
