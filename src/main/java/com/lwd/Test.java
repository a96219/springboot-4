package com.lwd;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class Test {

    @GetMapping("test")
    public String test(@NotNull Long id) {
        return "test api";
    }
}
