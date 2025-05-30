package pl.pkasiewicz.filterpackmate.domain.corner.exceptions;

public class CornerAlreadyExistsException extends RuntimeException {
    public CornerAlreadyExistsException(String message) {
        super(message);
    }
}
