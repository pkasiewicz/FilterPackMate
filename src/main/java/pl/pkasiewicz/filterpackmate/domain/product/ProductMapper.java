package pl.pkasiewicz.filterpackmate.domain.product;

import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;

class ProductMapper {

    static Product mapToEntity(ProductRequestDto dto) {
        return Product.builder()
                .carton(dto.carton())
                .tray(dto.tray())
                .pallet(dto.pallet())
                .dividers(dto.dividers())
                .sides(dto.sides())
                .build();
    }

    static ProductResponseDto mapToDto(Product entity) {
        return ProductResponseDto.builder()
                .id(entity.getId())
                .carton(entity.getCarton())
                .tray(entity.getTray())
                .pallet(entity.getPallet())
                .dividers(entity.getDividers())
                .sides(entity.getSides())
                .build();
    }
}
