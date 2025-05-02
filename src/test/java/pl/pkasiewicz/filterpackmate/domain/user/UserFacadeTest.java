package pl.pkasiewicz.filterpackmate.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import pl.pkasiewicz.filterpackmate.domain.user.dto.UserRequestDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.UserResponseDto;
import pl.pkasiewicz.filterpackmate.domain.user.exceptions.UserNotFoundException;

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
        UserRequestDto requestDto = new UserRequestDto("user", "password");
        User returnedFromDb = new User(1L, "user", "password");

        when(userRepository.save(any(User.class))).thenReturn(returnedFromDb);

        // when
        UserResponseDto response = userFacade.registerUser(requestDto);

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
        UserResponseDto expectedDto = UserMapper.mapToUserResponseDto(returnedUser);

        // when
        UserResponseDto responseDto = userFacade.findByUsername(username);

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
        assertThrows(UserNotFoundException.class, () -> userFacade.findByUsername(username));

        verify(userRepository).findByUsername(username);
    }

    @Test
    void should_throw_exception_if_username_already_exists() {
        // given
        UserRequestDto requestDto = new UserRequestDto("user", "password");
        when(userRepository.save(any(User.class))).thenThrow(new DuplicateKeyException("user already exists"));

        // when & then
        assertThrows(DuplicateKeyException.class, () -> userFacade.registerUser(requestDto));
        verify(userRepository).save(any(User.class));
    }

}