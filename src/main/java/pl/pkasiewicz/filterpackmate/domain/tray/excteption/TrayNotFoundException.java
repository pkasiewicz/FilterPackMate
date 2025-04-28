package pl.pkasiewicz.filterpackmate.domain.tray.excteption;

public class TrayNotFoundException extends RuntimeException{
    public TrayNotFoundException(String message) {
        super(message);
    }
}
