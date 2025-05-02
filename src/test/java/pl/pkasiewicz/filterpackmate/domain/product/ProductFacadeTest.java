package pl.pkasiewicz.filterpackmate.domain.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import pl.pkasiewicz.filterpackmate.domain.carton.Carton;
import pl.pkasiewicz.filterpackmate.domain.divider.Divider;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.exceptions.ProductNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.side.Side;
import pl.pkasiewicz.filterpackmate.domain.tray.Tray;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductFacadeTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductFacade productFacade;

    @Test
    void should_save_product() {
        // given
        ProductRequestDto productRequestDto = new ProductRequestDto(
                new Carton(1L, "PCA-12", null),
                new Tray(1L, "DE165", null),
                Pallet.EURO,
                List.of(
                        new Divider(1L, "E-1", null)
                ),
                List.of(
                        new Side(1L, "BE900B", null)
                )
        );
        Product returnedFromDb = new Product(
                1L,
                new Carton(1L, "PCA-12", null),
                new Tray(1L, "DE165", null),
                Pallet.EURO,
                List.of(
                        new Divider(1L, "E-1", null)
                ),
                List.of(
                        new Side(1L, "BE900B", null)
                )
        );

        when(productRepository.save(any(Product.class))).thenReturn(returnedFromDb);

        // when
        ProductResponseDto response = productFacade.saveProduct(productRequestDto);

        // then
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(captor.capture());
        Product savedProduct = captor.getValue();

        assertThat(savedProduct.carton).isEqualTo(returnedFromDb.carton);
        assertThat(savedProduct.tray).isEqualTo(returnedFromDb.tray);
        assertThat(savedProduct.pallet).isEqualTo(returnedFromDb.pallet);
        assertThat(response.id()).isEqualTo(returnedFromDb.id);
    }


    @Test
    void should_return_all_products() {
        // given
        List<Product> products = List.of(
                new Product(1L, new Carton(1L, "PCA-1", null), new Tray(1L, "DE165", null), Pallet.EURO, null, null),
                new Product(2L, new Carton(2L, "PCA-2", null), new Tray(2L, "DE152", null), Pallet.GM6, null, null),
                new Product(3L, new Carton(3L, "PCA-3", null), new Tray(3L, "DE178", null), Pallet.HG5, null, null)
        );

        when(productRepository.findAll()).thenReturn(products);

        // when
        List<ProductResponseDto> response = productFacade.getAllProducts();

        // then
        verify(productRepository).findAll();
        assertThat(response).hasSize(3);
        assertThat(response).extracting(ProductResponseDto::carton)
                .extracting(Carton::getName)
                .containsExactly("PCA-1", "PCA-2", "PCA-3");
    }

    @Test
    void should_return_product_by_id() {
        // given
        Long id = 1L;
        Product product = new Product(
                1L,
                new Carton(1L, "PCA-12", null),
                new Tray(1L, "DE165", null),
                Pallet.EURO,
                List.of(
                        new Divider(1L, "E-1", null)
                ),
                List.of(
                        new Side(1L, "BE900B", null)
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
                new Carton(1L, "PCA-12", null),
                new Tray(1L, "DE165", null),
                Pallet.EURO,
                List.of(
                        new Divider(1L, "E-1", null)
                ),
                List.of(
                        new Side(1L, "BE900B", null)
                )
        );

        when(productRepository.save(any(Product.class))).thenThrow(new DuplicateKeyException("product already exists"));

        // when && then
        assertThrows(DuplicateKeyException.class, () -> productFacade.saveProduct(productRequestDto));
        verify(productRepository).save(any(Product.class));
    }
}