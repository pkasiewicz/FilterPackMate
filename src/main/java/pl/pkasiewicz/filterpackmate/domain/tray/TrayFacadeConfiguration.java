package pl.pkasiewicz.filterpackmate.domain.tray;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrayFacadeConfiguration {

    @Bean
    TrayFacade trayFacade(TrayRepository trayRepository) {
        return new TrayFacade(trayRepository);
    }
}
