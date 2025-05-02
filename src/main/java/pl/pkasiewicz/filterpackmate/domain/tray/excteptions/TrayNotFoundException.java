package pl.pkasiewicz.filterpackmate.domain.tray.excteptions;

public class TrayNotFoundException extends RuntimeException{
    public TrayNotFoundException(String message) {
        super(message);
    }
}
