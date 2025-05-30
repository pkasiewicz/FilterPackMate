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
import pl.pkasiewicz.filterpackmate.domain.product.ProductFacade;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductRequestDto;
import pl.pkasiewicz.filterpackmate.domain.product.dto.ProductResponseDto;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Tag(name = "Product Management", description = "Operations related to products")
public class ProductController {

    private final ProductFacade productFacade;

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

    @PostMapping
    @Operation(summary = "Create a new product", description = "Adds a new product to the system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created successfully",
                            content = @Content(schema = @Schema(implementation = ProductResponseDto.class)))
            })
    public ResponseEntity<ProductResponseDto> addProduct(
            @Parameter(description = "Product object to be created") @RequestBody ProductRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productFacade.saveProduct(dto));
    }
}