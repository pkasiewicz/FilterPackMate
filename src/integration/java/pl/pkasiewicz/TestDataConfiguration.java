package pl.pkasiewicz;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pkasiewicz.filterpackmate.domain.carton.CartonFacade;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerFacade;
import pl.pkasiewicz.filterpackmate.domain.product.ProductFacade;
import pl.pkasiewicz.filterpackmate.domain.side.SideFacade;
import pl.pkasiewicz.filterpackmate.domain.tray.TrayFacade;

@Configuration
@RequiredArgsConstructor
public class TestDataConfiguration {

    private final ProductFacade productFacade;
    private final CartonFacade cartonFacade;
    private final TrayFacade trayFacade;
    private final DividerFacade dividerFacade;
    private final SideFacade sideFacade;

    @Bean
    public TestData testData() {
        return new TestData(
                productFacade,
                cartonFacade,
                trayFacade,
                dividerFacade,
                sideFacade
        );
    }
}
