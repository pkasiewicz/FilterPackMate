package pl.pkasiewicz.filterpackmate.domain.corner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CornerFacadeConfiguration {

    @Bean
    CornerFacade cornerFacade(CornerRepository cornerRepository) {
        return new CornerFacade(cornerRepository);
    }
}
