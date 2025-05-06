package pl.pkasiewicz.filterpackmate.domain.divider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DividerFacadeConfiguration {

    @Bean
    DividerFacade dividerFacade(DividerRepository dividerRepository) {
        return new DividerFacade(dividerRepository);
    }
}
