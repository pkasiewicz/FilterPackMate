package pl.pkasiewicz.filterpackmate.domain.product;

import lombok.AllArgsConstructor;
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

import java.util.List;

@AllArgsConstructor
public class ProductFacade {

    private final ProductRepository productRepository;
    private final CartonFacade cartonFacade;
    private final TrayFacade trayFacade;
    private final DividerFacade dividerFacade;
    private final SideFacade sideFacade;

    public ProductResponseDto saveProduct(ProductRequestDto dto) {
        Product product = getProduct(dto);
        return ProductMapper.mapToDto(productRepository.save(product));
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

    private Product getProduct(ProductRequestDto dto) {
        Carton carton = getCarton(dto);
        Tray tray = getTray(dto);
        List<Divider> dividers = getDividers(dto);
        List<Side> sides = getSides(dto);

        return Product.builder()
                .carton(carton)
                .tray(tray)
                .pallet(dto.pallet())
                .dividers(dividers)
                .sides(sides)
                .build();
    }

    private Carton getCarton(ProductRequestDto dto) {
        return cartonFacade.getCartonEntityById(dto.cartonId());
    }

    private Tray getTray(ProductRequestDto dto) {
        return trayFacade.getTrayEntityById(dto.trayId());
    }

    private List<Divider> getDividers(ProductRequestDto dto) {
        return dto.dividerIds()
                .stream()
                .map(dividerFacade::getDividerEntityById)
                .toList();
    }

    private List<Side> getSides(ProductRequestDto dto) {
        return dto.sideIds()
                .stream()
                .map(sideFacade::getSideEntityById)
                .toList();
    }
}
