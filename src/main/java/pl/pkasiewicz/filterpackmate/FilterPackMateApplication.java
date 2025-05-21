package pl.pkasiewicz.filterpackmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.pkasiewicz.filterpackmate.infrastructure.security.jwt.JwtConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtConfigurationProperties.class})
public class FilterPackMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterPackMateApplication.class, args);
    }

}
