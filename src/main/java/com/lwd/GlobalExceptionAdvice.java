package com.lwd;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationException(ConstraintViolationException exception) {
        return exception.getConstraintViolations()
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
    }
}
