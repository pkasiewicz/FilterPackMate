package pl.pkasiewicz.filterpackmate.infrastructure.security.jwt;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.pkasiewicz.filterpackmate.infrastructure.error.ErrorResponse;

@RestControllerAdvice
@Log4j2
public class JwtAuthExceptionAdvice {

    @ExceptionHandler(JWTDecodeException.class)
    public ErrorResponse handleMalformedJwt() {
        return new ErrorResponse("Invalid JWT token", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidTokenSignature() {
        return new ErrorResponse("Invalid token signature", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ErrorResponse handleTokenExpired(TokenExpiredException e) {
        log.warn("Token expired: {}", e.getMessage());
        return new ErrorResponse("JWT token expired", HttpStatus.UNAUTHORIZED);
    }
}
