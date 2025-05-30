package pl.pkasiewicz.filterpackmate.infrastructure.carton.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pkasiewicz.filterpackmate.domain.carton.exceptions.CartonAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.carton.exceptions.CartonNotFoundException;
import pl.pkasiewicz.filterpackmate.infrastructure.error.ErrorResponse;

@ControllerAdvice(basePackageClasses = {CartonController.class})
@Log4j2
class CartonControllerErrorHandler {

    public static final String CARTON_ALREADY_EXISTS = "Carton already exists";
    public static final String CARTON_NOT_FOUND = "Carton not found";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CartonNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleCartonNotFoundException(CartonNotFoundException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(CARTON_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CartonAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponse handleCartonAlreadyExistsException(CartonAlreadyExistsException e) {
        log.warn("Carton already exists: {}", e.getMessage());
        return new ErrorResponse(CARTON_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handleCartonDuplicationException() {
        return new ErrorResponse(CARTON_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
