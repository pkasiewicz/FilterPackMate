package pl.pkasiewicz.filterpackmate.infrastructure.user.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pkasiewicz.filterpackmate.domain.user.exceptions.UsernameAlreadyExistsException;
import pl.pkasiewicz.filterpackmate.infrastructure.error.ErrorResponse;

@ControllerAdvice(basePackageClasses = {UserController.class})
@Log4j2
class UserControllerErrorHandler {

    public static final String BAD_CREDENTIALS = "Bad Credentials";
    public static final String USER_ALREADY_EXISTS = "User already exists";

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ErrorResponse handleBadCredentialsException(BadCredentialsException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponse handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        log.warn("Username already exists: {}", e.getMessage());
        return new ErrorResponse(USER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handleUserDuplicationException() {
        return new ErrorResponse(USER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
