package pl.pkasiewicz.filterpackmate.domain.product;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.exception.ProductNotFoundException;

import java.util.List;

@AllArgsConstructor
public class ProductFacade {

    private final ProductRepository productRepository;

    public ProductResponseDto saveProduct(ProductRequestDto product) {
        Product savedProduct = productRepository.save(ProductMapper.mapToEntity(product));
        return ProductMapper.mapToDto(savedProduct);
    }

    public ProductResponseDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::mapToDto)
                .orElseThrow(() -> new ProductNotFoundException("product not found"));
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::mapToDto)
                .toList();
    }

    public ProductResponseDto deleteProduct(ProductRequestDto product) {
        return null;
    }
}
