package pl.pkasiewicz.filterpackmate.infrastructure.divider.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pkasiewicz.filterpackmate.domain.divider.exceptions.DividerAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.divider.exceptions.DividerNotFoundException;
import pl.pkasiewicz.filterpackmate.infrastructure.error.ErrorResponse;

@ControllerAdvice(basePackageClasses = {DividerController.class})
@Log4j2
class DividerControllerErrorHandler {

    public static final String DIVIDER_ALREADY_EXISTS = "Divider already exists";
    public static final String DIVIDER_NOT_FOUND = "Divider not found";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DividerNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleDividerNotFoundException(DividerNotFoundException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(DIVIDER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DividerAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponse handleDividerAlreadyExistsException(DividerAlreadyExistsException e) {
        log.warn("Divider already exists: {}", e.getMessage());
        return new ErrorResponse(DIVIDER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handleDividerDuplicationException() {
        return new ErrorResponse(DIVIDER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
