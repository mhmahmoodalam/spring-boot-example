package com.fev.blog.blogger_app.controllers;

import com.fev.blog.blogger_app.dto.users.CreateUserRequest;
import com.fev.blog.blogger_app.dto.users.UserResponse;
import com.fev.blog.blogger_app.facade.ManageUserFacade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private ManageUserFacade manageUserFacade;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody CreateUserRequest newUserRequest) {
        var registerUser = manageUserFacade.register(newUserRequest);
        return ResponseEntity.ok(registerUser);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getLoggedInUserDetails(Authentication authentication) {
        var registerUser = manageUserFacade.getLoggedInUserDetails(authentication);
        return ResponseEntity.ok(registerUser);
    }


}
