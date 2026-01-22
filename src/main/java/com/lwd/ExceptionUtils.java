package com.lwd;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;

public class ExceptionUtils {

    public static String message(ConstraintViolationException exception) {
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

    private ExceptionUtils() {
    }
}
