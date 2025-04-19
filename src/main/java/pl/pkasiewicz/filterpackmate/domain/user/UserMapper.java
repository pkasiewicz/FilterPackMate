package pl.pkasiewicz.filterpackmate.domain.user;

import pl.pkasiewicz.filterpackmate.domain.user.dto.UserRequestDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.UserResponseDto;

class UserMapper {

     static User mapToEntity(UserRequestDto userRequestDto) {
        return User.builder()
                .username(userRequestDto.username())
                .password(userRequestDto.password())
                .build();
    }

    static UserResponseDto mapToUserResponseDto(User user) {
         return UserResponseDto.builder()
                 .id(user.id)
                 .username(user.username)
                 .password(user.password)
                 .build();
    }
}
