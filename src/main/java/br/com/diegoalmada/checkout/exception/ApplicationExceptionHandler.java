package br.com.diegoalmada.checkout.exception;

import br.com.diegoalmada.checkout.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductsBadRequest.class)
    public ResponseEntity<Object> handleProductsBadRequestException(ProductsBadRequest e) {
        Error error = new Error(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }
}
