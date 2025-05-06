package pl.pkasiewicz.filterpackmate.domain.side;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SideFacadeConfiguration {

    @Bean
    SideFacade sideFacade(SideRepository sideRepository) {
        return new SideFacade(sideRepository);
    }
}
