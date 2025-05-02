package pl.pkasiewicz.filterpackmate.domain.carton.exceptions;

public class CartonNotFoundException extends RuntimeException {
    public CartonNotFoundException(String message) {
        super(message);
    }
}
