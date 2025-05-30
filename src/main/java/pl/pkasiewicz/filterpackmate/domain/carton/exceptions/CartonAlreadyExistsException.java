package pl.pkasiewicz.filterpackmate.domain.carton.exceptions;

public class CartonAlreadyExistsException extends RuntimeException {
    public CartonAlreadyExistsException(String message) {
        super(message);
    }
}
