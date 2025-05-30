package pl.pkasiewicz.filterpackmate.infrastructure.side.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pkasiewicz.filterpackmate.domain.side.exceptions.SideAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.side.exceptions.SideNotFoundException;
import pl.pkasiewicz.filterpackmate.infrastructure.error.ErrorResponse;

@ControllerAdvice(basePackageClasses = {SideController.class})
@Log4j2
class SideControllerErrorHandler {

    public static final String SIDE_ALREADY_EXISTS = "Side already exists";
    public static final String SIDE_NOT_FOUND = "Side not found";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SideNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleSideNotFoundException(SideNotFoundException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(SIDE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SideAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponse handleSideAlreadyExistsException(SideAlreadyExistsException e) {
        log.warn("Side already exists: {}", e.getMessage());
        return new ErrorResponse(SIDE_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handleSideDuplicationException() {
        return new ErrorResponse(SIDE_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
