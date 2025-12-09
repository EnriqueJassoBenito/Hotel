package com.example.BackHotel.login.controller;

import com.example.BackHotel.login.model.LoginRequest;
import com.example.BackHotel.login.model.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return loginService.login(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}

