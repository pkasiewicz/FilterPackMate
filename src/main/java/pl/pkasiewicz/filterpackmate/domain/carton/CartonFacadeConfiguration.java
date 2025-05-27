package pl.pkasiewicz.filterpackmate.domain.carton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartonFacadeConfiguration {

    @Bean
    CartonFacade cartonFacade(CartonRepository cartonRepository) {
        return new CartonFacade(cartonRepository);
    }
}
