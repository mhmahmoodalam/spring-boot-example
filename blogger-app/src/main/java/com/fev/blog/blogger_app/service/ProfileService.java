package com.fev.blog.blogger_app.service;

import com.fev.blog.blogger_app.authentication.entity.SecurityUser;
import com.fev.blog.blogger_app.dto.users.CreateUserRequest;
import com.fev.blog.blogger_app.dto.users.UserResponse;
import com.fev.blog.blogger_app.entity.Profile;
import com.fev.blog.blogger_app.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {

    private ProfileRepository profileRepository;
    private ModelMapper modelMapper;

    public UserResponse registerUser(CreateUserRequest newUserRequest, SecurityUser securityUser) {
        var profile = modelMapper.map(newUserRequest, Profile.class);
        profile.setUsername(newUserRequest.getEmail().split("@")[0]);
        profile.setSecurityUser(securityUser);
        profileRepository.save(profile);
        return modelMapper.map(profile, UserResponse.class);
    }
}
