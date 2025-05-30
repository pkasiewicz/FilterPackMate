package pl.pkasiewicz.filterpackmate.domain.tray.excteptions;

public class TrayAlreadyExistsException extends RuntimeException {
    public TrayAlreadyExistsException(String message) {
        super(message);
    }
}
