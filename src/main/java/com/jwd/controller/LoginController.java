package com.jwd.controller;

import com.jwd.exception.CustomException;
import com.jwd.model.auth.Login;
import com.jwd.model.auth.LoginResponse;
import com.jwd.model.auth.User;
import com.jwd.security.JwtTokenProvider;
import com.jwd.service.auth.ILoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "login")
public class LoginController {
    @Autowired
    ILoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "Authenticates user and returns its JWT token")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<?> login(@ApiParam("loginForm") @RequestBody Login loginForm) {
        LoginResponse loginResponse = loginService.login(loginForm);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh_token")
    @ApiOperation(value = "Get token and refresh token when token has expired")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code=401, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<String> refreshToken(HttpServletRequest request,
                                          HttpServletResponse response) {
        String nextToken = loginService.refreshToken(request, response);

        return new ResponseEntity<String>(nextToken, HttpStatus.OK);
    }
}