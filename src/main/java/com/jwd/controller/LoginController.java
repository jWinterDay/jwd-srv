package com.jwd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String getUser() {
        return "yoooo";
    }
}