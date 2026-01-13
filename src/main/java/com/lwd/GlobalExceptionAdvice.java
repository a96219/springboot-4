package com.lwd;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionBodyDto> constraintViolationException(ConstraintViolationException exception) {
        var message = exception.getConstraintViolations()
                .stream()
                .findFirst()
                .map(constraintViolation -> {
                    var msg = constraintViolation.getMessage();
                    for (var path : constraintViolation.getPropertyPath()) {
                        if (path.getKind() == ElementKind.PARAMETER) {
                            return path.getName() + ":" + msg;
                        }
                    }

                    return msg;
                })
                .orElse(exception.getMessage());
        return ResponseEntity.ok()
                .body(new ExceptionBodyDto(message, null));
    }

    public record ExceptionBodyDto(@Nullable String message, @Nullable Object data) {
    }
}
