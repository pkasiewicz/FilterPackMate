package pl.pkasiewicz.filterpackmate.infrastructure.corner;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pkasiewicz.filterpackmate.domain.corner.exceptions.CornerAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.domain.corner.exceptions.CornerNotFoundException;
import pl.pkasiewicz.filterpackmate.infrastructure.error.ErrorResponse;

@ControllerAdvice(basePackageClasses = {CornerController.class})
@Log4j2
class CornerControllerErrorHandler {

    public static final String CORNER_ALREADY_EXISTS = "Corner already exists";
    public static final String CORNER_NOT_FOUND = "Corner not found";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CornerNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleCornerNotFoundException(CornerNotFoundException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(CORNER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CornerAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponse handleCornerAlreadyExistsException(CornerAlreadyExistsException e) {
        log.warn("Corner already exists: {}", e.getMessage());
        return new ErrorResponse(CORNER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handleCornerDuplicationException() {
        return new ErrorResponse(CORNER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
