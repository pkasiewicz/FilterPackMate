package pl.pkasiewicz.filterpackmate.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import pl.pkasiewicz.filterpackmate.domain.user.dto.RegisterUserDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.RegistrationResultDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.UserDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserFacadeTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserFacade userFacade;

    @Test
    void should_create_a_user() {
        // given
        RegisterUserDto requestDto = new RegisterUserDto("user", "password");
        User returnedFromDb = new User(1L, "user", "password");

        when(userRepository.save(any(User.class))).thenReturn(returnedFromDb);

        // when
        RegistrationResultDto response = userFacade.registerUser(requestDto);

        // then
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User savedUser = captor.getValue();

        assertThat(savedUser.getUsername()).isEqualTo(requestDto.username());
        assertThat(savedUser.getPassword()).isEqualTo(requestDto.password());
        assertThat(response.username()).isEqualTo("user");
    }

    @Test
    void should_find_user_by_username() {
        // given
        String username = "user";
        User returnedUser = new User(1L, "user", "password");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(returnedUser));
        UserDto expectedDto = UserMapper.mapToUserDto(returnedUser);

        // when
        UserDto responseDto = userFacade.findByUsername(username);

        // then
        verify(userRepository).findByUsername(username);
        assertThat(responseDto).isEqualTo(expectedDto);
    }

    @Test
    void should_throw_exception_if_user_not_found() {
        // given
        String username = "username";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // when & then
        assertThrows(BadCredentialsException.class, () -> userFacade.findByUsername(username));

        verify(userRepository).findByUsername(username);
    }

    @Test
    void should_throw_exception_if_username_already_exists() {
        // given
        RegisterUserDto requestDto = new RegisterUserDto("user", "password");
        when(userRepository.save(any(User.class))).thenThrow(new DuplicateKeyException("user already exists"));

        // when & then
        assertThrows(DuplicateKeyException.class, () -> userFacade.registerUser(requestDto));
        verify(userRepository).save(any(User.class));
    }

}