package com.fev.blog.blogger_app.facade;

import com.fev.blog.blogger_app.authentication.entity.SecurityUser;
import com.fev.blog.blogger_app.dto.users.CreateUserRequest;
import com.fev.blog.blogger_app.dto.users.UserResponse;
import com.fev.blog.blogger_app.service.ProfileService;
import com.fev.blog.blogger_app.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManageUserFacade {

    private UserService userService;
    private ProfileService profileService;

    @Transactional
    public UserResponse register(CreateUserRequest newUserRequest) {
        var securityUser = userService.createSecurityUser(newUserRequest);
        return profileService.registerUser(newUserRequest, securityUser);
    }


    public UserResponse getLoggedInUserDetails(Authentication authentication) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        var userResponse = userService.getByUserName(userDetails.getUsername())
                .map(this::mapUserDataToResponse);
        return userResponse.orElse(null);
    }

    private UserResponse mapUserDataToResponse(SecurityUser securityUser) {
        UserResponse userResponse = new UserResponse();
        var profile = securityUser.getProfile();
        userResponse.setId(profile.getId());
        userResponse.setEmail(profile.getEmail());
        userResponse.setBio(profile.getBio());
        userResponse.setFirstName(profile.getFirstName());
        userResponse.setLastName(profile.getLastName());
        userResponse.setImage(profile.getImage());
        userResponse.setUsername(profile.getUsername());
        userResponse.setToken(securityUser.getToken());
        return userResponse;
    }
}
