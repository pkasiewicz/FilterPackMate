package pl.pkasiewicz.filterpackmate.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import pl.pkasiewicz.filterpackmate.domain.user.dto.RegisterUserDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.RegistrationResultDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.UserDto;
import pl.pkasiewicz.filterpackmate.domain.user.exceptions.UsernameAlreadyExistsException;


@AllArgsConstructor
public class UserFacade {

    private static final String USER_NOT_FOUND = "User not found";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    private final UserRepository userRepository;

    public RegistrationResultDto registerUser(RegisterUserDto registerUserDto) {
        if (userRepository.findByUsername(registerUserDto.username()).isPresent()) {
            throw new UsernameAlreadyExistsException(USERNAME_ALREADY_EXISTS);
        }

        User savedUser = userRepository.save(UserMapper.mapToEntity(registerUserDto));
        return UserMapper.mapToRegistrationResultDto(savedUser);
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::mapToUserDto)
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND));
    }
}
