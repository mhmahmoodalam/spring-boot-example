package com.fev.blog.blogger_app.controllers;

import com.fev.blog.blogger_app.authentication.service.AuthService;
import com.fev.blog.blogger_app.dto.users.UserLoginRequest;
import com.fev.blog.blogger_app.dto.users.UserLoginResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> register(@Valid @RequestBody UserLoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }


}
