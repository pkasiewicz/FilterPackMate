package pl.pkasiewicz.filterpackmate.infrastructure.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pkasiewicz.filterpackmate.domain.product.dto.PackagingCalculationDto;
import pl.pkasiewicz.filterpackmate.domain.product.ProductFacade;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductPackagingCalculationRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Tag(name = "Product Management", description = "Operations related to products")
public class ProductController {

    private final ProductFacade productFacade;

    @PostMapping("/calculate")
    public ResponseEntity<List<PackagingCalculationDto>> createProduct(@RequestBody List<ProductPackagingCalculationRequestDto> request) {
        List<PackagingCalculationDto> productsWithPackaging = productFacade.getProductsWithPackaging(request);
        return ResponseEntity.ok(productsWithPackaging);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Returns a single product based on the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved product",
                            content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    public ResponseEntity<ProductResponseDto> getProductById(
            @Parameter(description = "ID of the product to retrieve", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(productFacade.getProductById(id));
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Returns a list of all available products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productFacade.getAllProducts());
    }

    @PostMapping("/add")
    @Operation(summary = "Create a new product", description = "Adds a new product to the system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created successfully",
                            content = @Content(schema = @Schema(implementation = ProductResponseDto.class)))
            })
    public ResponseEntity<ProductResponseDto> addProduct(
            @Parameter(description = "Product object to be created") @RequestBody ProductRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productFacade.saveProduct(dto));
    }

//    @PutMapping("/{id}/edit")
//    public ResponseEntity<ProductResponseDto> updateProduct(
//            @PathVariable Long id,
//            @RequestBody ProductRequestDto dto) {
//        return ResponseEntity.ok(productFacade.updateProduct(id, dto));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        productFacade.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
}