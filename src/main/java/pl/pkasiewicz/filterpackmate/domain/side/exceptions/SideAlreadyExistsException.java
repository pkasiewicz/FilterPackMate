package pl.pkasiewicz.filterpackmate.domain.side.exceptions;

public class SideAlreadyExistsException extends RuntimeException {
    public SideAlreadyExistsException(String message) {
        super(message);
    }
}
