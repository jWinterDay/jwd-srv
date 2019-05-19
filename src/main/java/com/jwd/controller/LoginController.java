package com.jwd.controller;

import com.jwd.model.auth.Login;
import com.jwd.model.auth.User;
import com.jwd.service.auth.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    ILoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login loginForm) {

        User user = loginService.findByEmail(loginForm.getEmail());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}