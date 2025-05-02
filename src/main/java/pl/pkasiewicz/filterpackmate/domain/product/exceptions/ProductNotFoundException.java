package pl.pkasiewicz.filterpackmate.domain.product.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}

