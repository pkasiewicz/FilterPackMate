package pl.pkasiewicz.filterpackmate.domain.product;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.filterpackmate.domain.carton.Carton;
import pl.pkasiewicz.filterpackmate.domain.carton.CartonFacade;
import pl.pkasiewicz.filterpackmate.domain.corner.Corner;
import pl.pkasiewicz.filterpackmate.domain.corner.CornerFacade;
import pl.pkasiewicz.filterpackmate.domain.corner.exceptions.CornerNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.divider.Divider;
import pl.pkasiewicz.filterpackmate.domain.divider.DividerFacade;
import pl.pkasiewicz.filterpackmate.domain.product.dto.PackagingCalculationDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductPackagingCalculationRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;
import pl.pkasiewicz.filterpackmate.domain.product.exceptions.ProductAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.product.exceptions.ProductNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.side.Side;
import pl.pkasiewicz.filterpackmate.domain.side.SideFacade;
import pl.pkasiewicz.filterpackmate.domain.side.exceptions.SideNotFoundException;
import pl.pkasiewicz.filterpackmate.domain.tray.Tray;
import pl.pkasiewicz.filterpackmate.domain.tray.TrayFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProductFacade {

    public static final String PRODUCT_ALREADY_EXISTS = "Product already exists";
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    private final ProductRepository productRepository;
    private final CartonFacade cartonFacade;
    private final TrayFacade trayFacade;
    private final DividerFacade dividerFacade;
    private final SideFacade sideFacade;
    private final CornerFacade cornerFacade;
    private final ProductCalculationService productCalculationService;

    public List<PackagingCalculationDto> getProductsWithPackaging(List<ProductPackagingCalculationRequestDto> productDtos) {
        return productDtos
                .stream()
                .map(dto -> {
                    Product product = productRepository.findById(dto.productId())
                            .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
                    return productCalculationService.calculatePackingMaterials(product, dto.productQty(), dto.isLotted());
                })
                .toList();
    }

    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        if (productRepository.existsByName(productRequestDto.name())) {
            throw new ProductAlreadyExistsException(PRODUCT_ALREADY_EXISTS);
        }

        Product savedProduct = productRepository.save(getProduct(productRequestDto));

        List<ProductSide> productSides = createProductSides(savedProduct, productRequestDto);
        List<ProductCorner> productCorners = createProductCorners(savedProduct, productRequestDto);

        savedProduct.setProductSides(productSides);
        savedProduct.setProductCorners(productCorners);

        return ProductMapper.mapToDto(productRepository.save(savedProduct));
    }

    public ProductResponseDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::mapToDto)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
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

        return Product.builder()
                .name(dto.name())
                .carton(carton)
                .filtersPerCarton(dto.filtersPerCarton())
                .tray(tray)
                .pallet(Pallet.valueOf(dto.palletType()))
                .cartonsPerPallet(dto.cartonsPerPallet())
                .filtersPerPallet(dto.filtersPerPallet())
                .dividers(dividers)
                .build();
    }

    private Carton getCarton(ProductRequestDto dto) {
        return cartonFacade.getCartonEntityById(dto.cartonId());
    }

    private Tray getTray(ProductRequestDto dto) {
        return trayFacade.getTrayEntityById(dto.trayId());
    }

    private List<Divider> getDividers(ProductRequestDto dto) {
        if (dto.dividerIds() == null) {
            return Collections.emptyList();
        }

        return dto.dividerIds()
                .stream()
                .flatMap(id -> dividerFacade.getDividerEntityById(id).stream())
                .toList();
    }

    private List<ProductSide> createProductSides(Product product, ProductRequestDto dto) {
        List<ProductSide> productSides = new ArrayList<>();

        if (dto.sideIds() != null) {
            dto.sideIds()
                    .forEach(sideId -> productSides.add(createProductSide(product, sideId, false)));
        }

        if (dto.lottedSideIds() != null) {
            dto.lottedSideIds()
                    .forEach(sideId -> productSides.add(createProductSide(product, sideId, true)));
        }

        return productSides;
    }

    private ProductSide createProductSide(Product product, Long sideId, boolean isLotted) {
        ProductSideId id = new ProductSideId(product.getId(), sideId);
        Side side = sideFacade.getSideEntityById(sideId).orElseThrow(() -> new SideNotFoundException("Side not found"));

        return new ProductSide(id, product, side, isLotted);
    }

    private List<ProductCorner> createProductCorners(Product product, ProductRequestDto dto) {
        List<ProductCorner> productCorner = new ArrayList<>();

        if (dto.cornerId() != null) {
            productCorner.add(createProductCorner(product, dto.cornerId(), false));
        }

        if (dto.lottedCornerId() != null) {
            productCorner.add(createProductCorner(product, dto.lottedCornerId(), true));
        }

        return productCorner;
    }

    private ProductCorner createProductCorner(Product product, Long cornerId, boolean isLotted) {
        ProductCornerId id = new ProductCornerId(product.getId(), cornerId);
        Corner corner = cornerFacade.getCornerEntityById(cornerId).orElseThrow(() -> new CornerNotFoundException("Corner not found"));

        return new ProductCorner(id, product, corner, isLotted);
    }
}
