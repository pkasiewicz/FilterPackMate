package pl.pkasiewicz.filterpackmate.domain.user;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.user.dto.UserRequestDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.UserResponseDto;
import pl.pkasiewicz.filterpackmate.domain.user.exceptions.UserNotFoundException;


@AllArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User savedUser = userRepository.save(UserMapper.mapToEntity(userRequestDto));
        return UserMapper.mapToUserResponseDto(savedUser);
    }

    public UserResponseDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::mapToUserResponseDto)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }
}
