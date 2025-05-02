package pl.pkasiewicz.filterpackmate.domain.side.exceptions;

public class SideNotFoundException extends RuntimeException {
    public SideNotFoundException(String message) {
        super(message);
    }
}
