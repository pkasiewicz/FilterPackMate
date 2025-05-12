package pl.pkasiewicz.filterpackmate.domain.product;

import org.junit.jupiter.api.Test;
import pl.pkasiewicz.filterpackmate.domain.carton.Carton;
import pl.pkasiewicz.filterpackmate.domain.carton.exceptions.CartonNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.corner.Corner;
import pl.pkasiewicz.filterpackmate.domain.divider.Divider;
import pl.pkasiewicz.filterpackmate.domain.tray.Tray;
import pl.pkasiewicz.filterpackmate.domain.tray.excteptions.TrayNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductCalculationServiceTest {

    private final ProductCalculationService service = new ProductCalculationService();

    @Test
    void should_calculate_all_fields_correctly_for_valid_product() {
        // given
        Product product = new Product(
                1L,
                "BA24",
                new Carton(1L, "PC-A-19", new ArrayList<>()),
                6,
                new Tray(1L, "DE163", new ArrayList<>()),
                Pallet.EURO,
                20,
                120,
                List.of(new Divider(1L, "EA", 5400, new ArrayList<>())),
                new ArrayList<>(),
                null
        );
        int productQty = 1000;

        // when
        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty);

        // then
        assertAll(
                () -> assertThat(result.productId()).isEqualTo(1L),
                () -> assertThat(result.productName()).isEqualTo("BA24"),
                () -> assertThat(result.productQty()).isEqualTo(1000),
                () -> assertThat(result.cartonName()).isEqualTo("PC-A-19"),
                () -> assertThat(result.cartonQty()).isEqualTo((int) Math.ceil((double) productQty/ product.getFiltersPerCarton())),
                () -> assertThat(result.trayName()).isEqualTo("DE163"),
                () -> assertThat(result.trayQty()).isEqualTo(result.cartonQty() * 2),
                () -> assertThat(result.dividers()).hasSize(1),
                () -> assertThat(result.sides()).isNotNull(),
                () -> assertThat(result.cornerQty()).isNull()
        );
    }

    @Test
    void should_throw_when_carton_is_null() {
        Product product = new Product(
                1L,
                "BA24",
                null,
                6,
                new Tray(1L, "DE163", new ArrayList<>()),
                Pallet.EURO,
                20,
                120,
                List.of(new Divider(1L, "EA", 5400, new ArrayList<>())),
                new ArrayList<>(),
                null
        );
        int productQty = 1000;

        assertThatThrownBy(() ->
                service.calculatePackingMaterials(product, productQty)
        ).isInstanceOf(CartonNotFoundException.class);
    }

    @Test
    void should_throw_when_tray_is_null() {
        Product product = new Product(
                1L,
                "BA24",
                new Carton(1L, "PC-A-19", new ArrayList<>()),
                6,
                null,
                Pallet.EURO,
                20,
                120,
                List.of(new Divider(1L, "EA", 5400, new ArrayList<>())),
                new ArrayList<>(),
                null
        );
        int productQty = 1000;

        assertThatThrownBy(() ->
                service.calculatePackingMaterials(product, productQty)
        ).isInstanceOf(TrayNotFoundException.class);
    }

    @Test
    void should_throw_exception_when_cartonsPerPallet_is_zero() {
        Product product = new Product(
                1L,
                "BA24",
                new Carton(1L, "PC-A-19", new ArrayList<>()),
                0,
                new Tray(1L, "DE163", new ArrayList<>()),
                Pallet.EURO,
                0,
                120,
                List.of(new Divider(1L, "EA", 5400, new ArrayList<>())),
                new ArrayList<>(),
                null
        );
        int productQty = 1000;

        assertThatThrownBy(() ->
                service.calculatePackingMaterials(product, productQty)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Cartons per pallet cannot be zero");
    }

    @Test
    void should_throw_exception_when_filtersPerPallet_is_zero() {
        Product product = new Product(
                1L,
                "BA24",
                new Carton(1L, "PC-A-19", new ArrayList<>()),
                6,
                new Tray(1L, "DE163", new ArrayList<>()),
                Pallet.EURO,
                20,
                0,
                List.of(new Divider(1L, "EA", 5400, new ArrayList<>())),
                new ArrayList<>(),
                null
        );
        int productQty = 1000;

        assertThatThrownBy(() ->
                service.calculatePackingMaterials(product, productQty)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Filters per pallet cannot be zero");
    }

    @Test
    void should_handle_empty_dividers_list() {
        Product product = new Product(
                1L,
                "BA24",
                new Carton(1L, "PC-A-19", new ArrayList<>()),
                6,
                new Tray(1L, "DE163", new ArrayList<>()),
                Pallet.EURO,
                20,
                120,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
        int productQty = 1000;

        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty);

        assertThat(result.dividers()).isEmpty();
    }

    @Test
    void should_handle_empty_sides_list() {
        Product product = new Product(
                1L,
                "BA24",
                new Carton(1L, "PC-A-19", new ArrayList<>()),
                6,
                new Tray(1L, "DE163", new ArrayList<>()),
                Pallet.EURO,
                20,
                120,
                List.of(new Divider(1L, "EA", 5400, new ArrayList<>())),
                new ArrayList<>(),
                null
        );
        int productQty = 1000;

        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty);

        assertThat(result.sides()).isEmpty();
    }

    @Test
    void should_handle_zero_productQty() {
        Product product = new Product(
                1L,
                "BA24",
                new Carton(1L, "PC-A-19", new ArrayList<>()),
                6,
                new Tray(1L, "DE163", new ArrayList<>()),
                Pallet.EURO,
                20,
                120,
                List.of(new Divider(1L, "EA", 5400, new ArrayList<>())),
                new ArrayList<>(),
                null
        );
        int productQty = 0;

        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty);

        assertAll(
                () -> assertThat(result.cartonQty()).isEqualTo(0),
                () -> assertThat(result.trayQty()).isEqualTo(0),
                () -> assertThat(result.cornerQty()).isNull() // or 0, depending on your handling
        );
    }

    @Test
    void should_calculate_corners_when_corner_present() {
        Product product = new Product(
                1L,
                "BA24",
                new Carton(1L, "PC-A-19", new ArrayList<>()),
                6,
                new Tray(1L, "DE163", new ArrayList<>()),
                Pallet.EURO,
                20,
                120,
                List.of(new Divider(1L, "EA", 5400, new ArrayList<>())),
                new ArrayList<>(),
                new Corner(1L, "CP900", new ArrayList<>())
        );
        int productQty = 1000;
        int palletCorners = 4;

        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty);

        assertThat(result.cornerQty()).isNotNull();
        assertThat(result.cornerQty()).isEqualTo((int) Math.ceil((double) productQty / product.getFiltersPerPallet()) * palletCorners);
    }
}