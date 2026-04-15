package com.springproject.simple.web.app.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "application", "simple-web-app",
                "status", "running",
                "message", "Secure CI demo API is available");
    }
}
