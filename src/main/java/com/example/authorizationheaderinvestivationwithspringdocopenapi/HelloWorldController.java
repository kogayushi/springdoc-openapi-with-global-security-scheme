package com.example.authorizationheaderinvestivationwithspringdocopenapi;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HelloWorldController {

    @Operation
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "hello world";
    }
}
