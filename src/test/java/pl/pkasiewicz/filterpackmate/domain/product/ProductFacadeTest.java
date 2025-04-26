package pl.pkasiewicz.filterpackmate.domain.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pkasiewicz.filterpackmate.domain.carton.Carton;
import pl.pkasiewicz.filterpackmate.domain.divider.Divider;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.side.Side;
import pl.pkasiewicz.filterpackmate.domain.tray.Tray;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        // when
        when(productRepository.save(any(Product.class))).thenReturn(returnedFromDb);
        productFacade.addNewProduct(productRequestDto);

        // then
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(captor.capture());
        Product savedProduct = captor.getValue();
        assertThat(savedProduct.carton).isEqualTo(returnedFromDb.carton);
        assertThat(savedProduct.tray).isEqualTo(returnedFromDb.tray);
        assertThat(savedProduct.pallet).isEqualTo(returnedFromDb.pallet);

    }


    @Test
    void should_return_all_products() {
        // given

        // when

        // then

    }

    @Test
    void should_delete_product() {
        // given

        // when

        // then

    }

    @Test
    void should_return_product_by_id() {
        // given

        // when

        // then

    }

    @Test
    void should_throw_exception_when_product_with_given_id_does_not_exist() {
        // given

        // when

        // then

    }

    @Test
    void should_throw_exception_when_product_with_given_name_already_exist() {
        // given

        // when

        // then

    }
}