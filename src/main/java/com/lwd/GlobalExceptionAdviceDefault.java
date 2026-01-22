package com.lwd;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Profile("default")
@RestControllerAdvice
public class GlobalExceptionAdviceDefault {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionBodyDto> constraintViolationException(ConstraintViolationException exception) {
        var message = ExceptionUtils.message(exception);

        return ResponseEntity.ok()
                .body(new ExceptionBodyDto(message, null));
    }

    public record ExceptionBodyDto(String message, Object data) {
    }
}
