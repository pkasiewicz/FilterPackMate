package pl.pkasiewicz.filterpackmate.domain.product;

import org.junit.jupiter.api.Test;
import pl.pkasiewicz.filterpackmate.domain.carton.Carton;
import pl.pkasiewicz.filterpackmate.domain.carton.exceptions.CartonNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.corner.Corner;
import pl.pkasiewicz.filterpackmate.domain.divider.Divider;
import pl.pkasiewicz.filterpackmate.domain.product.dto.PackagingCalculationDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductCornerDto;
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
                List.of(),
                List.of()
        );
        int productQty = 1000;

        // when
        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty, false);

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
                () -> assertThat(result.corners()).isNotNull()
        );
    }

    @Test
    void should_throw_exception_when_carton_is_null() {
        // given && when
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
                List.of(),
                List.of()
        );
        int productQty = 1000;

        // then
        assertThatThrownBy(() ->
                service.calculatePackingMaterials(product, productQty, false)
        ).isInstanceOf(CartonNotFoundException.class);
    }

    @Test
    void should_throw_exception_when_tray_is_null() {
        // given && when
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
                List.of(),
                List.of()
        );
        int productQty = 1000;

        // then
        assertThatThrownBy(() ->
                service.calculatePackingMaterials(product, productQty, false)
        ).isInstanceOf(TrayNotFoundException.class);
    }

    @Test
    void should_throw_exception_when_cartonsPerPallet_is_zero() {
        // given && when
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
                List.of(),
                List.of()
        );
        int productQty = 1000;

        // then
        assertThatThrownBy(() ->
                service.calculatePackingMaterials(product, productQty, false)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Cartons per pallet cannot be zero");
    }

    @Test
    void should_throw_exception_when_filtersPerPallet_is_zero() {
        // given && when
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
                List.of(),
                List.of()
        );
        int productQty = 1000;

        // then
        assertThatThrownBy(() ->
                service.calculatePackingMaterials(product, productQty, false)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Filters per pallet cannot be zero");
    }

    @Test
    void should_handle_empty_dividers_list() {
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
                List.of(),
                List.of(),
                List.of()
        );
        int productQty = 1000;

        // when
        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty, false);

        // then
        assertThat(result.dividers()).isEmpty();
    }

    @Test
    void should_handle_empty_sides_list() {
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
                List.of(),
                List.of()
        );
        int productQty = 1000;

        // when
        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty, false);

        // then
        assertThat(result.sides()).isEmpty();
    }

    @Test
    void should_handle_zero_productQty() {
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
                List.of(),
                List.of()
        );
        int productQty = 0;

        // when
        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty, false);

        // then
        assertAll(
                () -> assertThat(result.cartonQty()).isEqualTo(0),
                () -> assertThat(result.trayQty()).isEqualTo(0)
        );
    }

    @Test
    void should_calculate_corners_when_corner_present() {
        // given
        Corner corner = new Corner(1L, "CP900", new ArrayList<>());

        ProductCorner productCorner = new ProductCorner(
                new ProductCornerId(1L, 1L),
                null,
                corner,
                false
        );

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
                List.of(),
                List.of(productCorner)
        );

        int productQty = 1000;
        int palletCorners = 4;

        // when
        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty, false);

        // then
        int expectedCornerQty = (int) Math.ceil((double) productQty / product.getFiltersPerPallet()) * palletCorners;
        List<ProductCornerDto> list = result.corners().stream().toList();

        assertThat(list.get(0).corner().cornerQty()).isEqualTo(expectedCornerQty);
        assertThat(list.get(0).corner().name()).isEqualTo("CP900");
    }

    @Test
    void should_calculate_lotted_corners_when_product_is_lotted() {
        // given
        Corner lottedCorner = new Corner(1L, "CP850", new ArrayList<>());

        ProductCorner productCorner = new ProductCorner(
                new ProductCornerId(1L, 1L),
                null,
                lottedCorner,
                true
        );

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
                List.of(),
                List.of(productCorner)
        );

        int productQty = 1000;
        int palletCorners = 4;

        // when
        PackagingCalculationDto result = service.calculatePackingMaterials(product, productQty, true);

        // then
        int expectedCornerQty = (int) Math.ceil((double) productQty / product.getFiltersPerPallet()) * palletCorners;
        List<ProductCornerDto> list = result.corners().stream().toList();

        assertThat(list.get(0).corner().cornerQty()).isEqualTo(expectedCornerQty);
        assertThat(list.get(0).corner().name()).isEqualTo("CP850");
    }
}