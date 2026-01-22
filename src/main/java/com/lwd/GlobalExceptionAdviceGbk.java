package com.lwd;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Profile("gbk")
@RestControllerAdvice
public class GlobalExceptionAdviceGbk {

    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationException(ConstraintViolationException exception) {
        return ExceptionUtils.message(exception);
    }
}
