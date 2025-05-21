package pl.pkasiewicz.filterpackmate.infrastructure.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.pkasiewicz.filterpackmate.domain.user.UserFacade;
import pl.pkasiewicz.filterpackmate.domain.user.dto.UserDto;

import java.util.Collections;

@AllArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        UserDto userDto = userFacade.findByUsername(username);
        return getUser(userDto);
    }

    private User getUser(UserDto user) {
        return new User(
                user.username(),
                user.password(),
                Collections.emptyList());
    }
}
