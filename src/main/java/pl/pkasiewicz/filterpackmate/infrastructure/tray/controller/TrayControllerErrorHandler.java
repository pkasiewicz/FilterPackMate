package pl.pkasiewicz.filterpackmate.infrastructure.tray.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pkasiewicz.filterpackmate.domain.tray.excteptions.TrayAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.tray.excteptions.TrayNotFoundException;
import pl.pkasiewicz.filterpackmate.infrastructure.error.ErrorResponse;

@ControllerAdvice(basePackageClasses = {TrayController.class})
@Log4j2
class TrayControllerErrorHandler {

    public static final String TRAY_ALREADY_EXISTS = "Tray already exists";
    public static final String TRAY_NOT_FOUND = "Tray not found";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TrayNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleTrayNotFoundException(TrayNotFoundException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(TRAY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TrayAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponse handleTrayAlreadyExistsException(TrayAlreadyExistsException e) {
        log.warn("Tray already exists: {}", e.getMessage());
        return new ErrorResponse(TRAY_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handleTrayDuplicationException() {
        return new ErrorResponse(TRAY_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
