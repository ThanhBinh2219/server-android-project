package com.commic.v1.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Hello {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}