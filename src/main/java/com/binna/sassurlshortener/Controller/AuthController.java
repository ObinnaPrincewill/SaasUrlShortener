package com.binna.sassurlshortener.Controller;

import com.binna.sassurlshortener.Auth.JwtService;
import com.binna.sassurlshortener.DTOs.Requests.CreateUserRequest;
import com.binna.sassurlshortener.DTOs.Response.AuthResponse;
import com.binna.sassurlshortener.DTOs.Response.ResponseWrapper;
import com.binna.sassurlshortener.Service.Implementation.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@ToString
@Slf4j
public class AuthController {

    private final UserServiceImpl service;
    private final JwtService jwtservice;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/signup")
    public ResponseWrapper<AuthResponse> signup(@RequestBody CreateUserRequest payload) {
        log.info("AUTH LOG: Signup payload:{}", payload);
        return service.signup(payload);
    }

//    @PostMapping("/signup")
//    public ResponseWrapper<AuthResponse> logout(@RequestBody CreateUserRequest payload) {
//        return service.signup(payload);
//    }
}