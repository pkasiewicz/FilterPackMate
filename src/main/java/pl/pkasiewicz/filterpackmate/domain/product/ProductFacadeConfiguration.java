package pl.pkasiewicz.filterpackmate.domain.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pkasiewicz.filterpackmate.domain.carton.CartonFacade;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerFacade;
import pl.pkasiewicz.filterpackmate.domain.side.SideFacade;
import pl.pkasiewicz.filterpackmate.domain.tray.TrayFacade;

@Configuration
class ProductFacadeConfiguration {

    @Bean
    ProductFacade productFacade(ProductRepository productRepository,
                                CartonFacade cartonFacade,
                                TrayFacade trayFacade,
                                DividerFacade dividerFacade,
                                SideFacade sideFacade) {
        return new ProductFacade(productRepository, cartonFacade, trayFacade, dividerFacade, sideFacade);
    }
}
