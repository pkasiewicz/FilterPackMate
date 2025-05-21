package pl.pkasiewicz.filterpackmate.domain.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFacadeConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository) {
        return new UserFacade(userRepository);
    }
}
