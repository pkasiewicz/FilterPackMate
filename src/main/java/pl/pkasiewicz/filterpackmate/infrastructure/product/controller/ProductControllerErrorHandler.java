package pl.pkasiewicz.filterpackmate.infrastructure.product.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pkasiewicz.filterpackmate.domain.product.exceptions.ProductAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.product.exceptions.ProductNotFoundException;
import pl.pkasiewicz.filterpackmate.infrastructure.error.ErrorResponse;

@ControllerAdvice(basePackageClasses = {ProductController.class})
@Log4j2
class ProductControllerErrorHandler {

    public static final String PRODUCT_ALREADY_EXISTS = "Product already exists";
    public static final String PRODUCT_NOT_FOUND = "Product not found";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProductAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponse handleProductAlreadyExistsException(ProductAlreadyExistsException e) {
        log.warn("Product already exists: {}", e.getMessage());
        return new ErrorResponse(PRODUCT_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handleProductDuplicationException() {
        return new ErrorResponse(PRODUCT_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
