package pl.pkasiewicz.filterpackmate.domain.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import pl.pkasiewicz.filterpackmate.domain.carton.Carton;
import pl.pkasiewicz.filterpackmate.domain.carton.CartonFacade;
import pl.pkasiewicz.filterpackmate.domain.divider.Divider;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerFacade;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.exceptions.ProductNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.side.Side;
import pl.pkasiewicz.filterpackmate.domain.side.SideFacade;
import pl.pkasiewicz.filterpackmate.domain.tray.Tray;
import pl.pkasiewicz.filterpackmate.domain.tray.TrayFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductFacadeTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartonFacade cartonFacade;
    @Mock
    private TrayFacade trayFacade;
    @Mock
    private DividerFacade dividerFacade;
    @Mock
    private SideFacade sideFacade;
    @InjectMocks
    private ProductFacade productFacade;

    @Test
    void should_save_product() {
        // given
        ProductRequestDto productRequestDto = new ProductRequestDto(
                "A1",
                1L,
                1L,
                Pallet.EURO,
                List.of(1L),
                List.of(1L)
        );

        Product returnedFromDb = new Product(
                1L,
                "A1",
                new Carton(1L, "PCA-12", 1, new ArrayList<>()),
                new Tray(1L, "DE165", 1, new ArrayList<>()),
                Pallet.EURO,
                List.of(
                        new Divider(1L, "E-1", 100, new ArrayList<>())
                ),
                List.of(
                        new Side(1L, "BE900B", new ArrayList<>())
                )
        );

        when(cartonFacade.getCartonEntityById(anyLong())).thenReturn(new Carton(1L, "PCA-12", 1, new ArrayList<>()));
        when(trayFacade.getTrayEntityById(anyLong())).thenReturn(new Tray(1L, "DE165", 1, new ArrayList<>()));
        when(dividerFacade.getDividerEntityById(anyLong())).thenReturn(new Divider(1L, "E-1", 100, new ArrayList<>()));
        when(sideFacade.getSideEntityById(anyLong())).thenReturn(new Side(1L, "BE900B", new ArrayList<>()));

        when(productRepository.save(any(Product.class))).thenReturn(returnedFromDb);

        // when
        ProductResponseDto response = productFacade.saveProduct(productRequestDto);


        // then
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(captor.capture());
        Product savedProduct = captor.getValue();

        assertThat(savedProduct.getCarton().getId()).isEqualTo(returnedFromDb.getCarton().getId());
        assertThat(savedProduct.getTray().getId()).isEqualTo(returnedFromDb.getTray().getId());
        assertThat(savedProduct.getPallet()).isEqualTo(returnedFromDb.getPallet());
        assertThat(response.id()).isEqualTo(returnedFromDb.getId());
    }


    @Test
    void should_return_all_products() {
        // given
        List<Product> products = List.of(
                new Product(
                        1L,
                        "A1",
                        new Carton(1L, "PCA-1", 100, new ArrayList<>()),
                        new Tray(1L, "DE165", 10, new ArrayList<>()),
                        Pallet.EURO,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new Product(
                        2L,
                        "A2",
                        new Carton(2L, "PCA-2", 100, new ArrayList<>()),
                        new Tray(2L, "DE152", 10, new ArrayList<>()),
                        Pallet.GM6,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new Product(
                        3L,
                        "A3",
                        new Carton(3L, "PCA-3", 100, new ArrayList<>()),
                        new Tray(3L, "DE178", 10, new ArrayList<>()),
                        Pallet.HG5,
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );

        when(productRepository.findAll()).thenReturn(products);

        // when
        List<ProductResponseDto> response = productFacade.getAllProducts();

        // then
        verify(productRepository).findAll();
        assertThat(response).hasSize(3);
        assertThat(response).extracting(ProductResponseDto::cartonName)
                .containsExactly("PCA-1", "PCA-2", "PCA-3");
    }

    @Test
    void should_return_product_by_id() {
        // given
        Long id = 1L;
        Product product = new Product(
                1L,
                "A1",
                new Carton(1L, "PCA-12", 1, new ArrayList<>()),
                new Tray(1L, "DE165", 1, new ArrayList<>()),
                Pallet.EURO,
                List.of(
                        new Divider(1L, "E-1", 100, new ArrayList<>())
                ),
                List.of(
                        new Side(1L, "BE900B", new ArrayList<>())
                )
        );

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        ProductResponseDto productResponseDto = ProductMapper.mapToDto(product);

        // when
        ProductResponseDto responseDto = productFacade.getProductById(id);

        // then
        verify(productRepository).findById(id);
        assertThat(responseDto).isEqualTo(productResponseDto);
    }

    @Test
    void should_return_empty_list_when_no_products_exist() {
        when(productRepository.findAll()).thenReturn(List.of());

        List<ProductResponseDto> response = productFacade.getAllProducts();
        verify(productRepository).findAll();
        assertThat(response).isEmpty();
    }

    @Test
    void should_throw_exception_when_product_with_given_id_does_not_exist() {
        // given
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // when && then
        assertThrows(ProductNotFoundException.class, () -> productFacade.getProductById(id));

        verify(productRepository).findById(id);
    }

    @Test
    void should_throw_exception_when_product_with_given_name_already_exist() {
        // given
        ProductRequestDto productRequestDto = new ProductRequestDto(
                "A1",
                1L,
                1L,
                Pallet.EURO,
                List.of(1L),
                List.of(1L)
        );

        when(productRepository.save(any(Product.class))).thenThrow(new DuplicateKeyException("product already exists"));

        // when && then
        assertThrows(DuplicateKeyException.class, () -> productFacade.saveProduct(productRequestDto));
        verify(productRepository).save(any(Product.class));
    }
}