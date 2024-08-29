package com.fev.blog.blogger_app.service;

import com.fev.blog.blogger_app.authentication.entity.Authority;
import com.fev.blog.blogger_app.authentication.entity.SecurityUser;
import com.fev.blog.blogger_app.authentication.models.Roles;
import com.fev.blog.blogger_app.dto.users.CreateUserRequest;
import com.fev.blog.blogger_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public SecurityUser createSecurityUser(CreateUserRequest createUserRequest){
        var securityUser = userRepository.findBySecurityUserName(createUserRequest.getEmail());
        if(securityUser.isPresent()){
            throw  new UsernameNotFoundException("User Already exists");
        }
        var newUser = modelMapper.map(createUserRequest, SecurityUser.class);
        newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        newUser.setSecurityUserName(createUserRequest.getEmail());
        userRepository.save(newUser);
        var authorities = new HashSet<Authority>();
        if(createUserRequest.isAdmin()){
            authorities.add(Roles.ADMIN.toAuthority());
            authorities.add(Roles.MODERATOR.toAuthority());
        }
        if(createUserRequest.isModerator()){
            authorities.add(Roles.MODERATOR.toAuthority());
        }
        authorities.add(Roles.READER.toAuthority());
        newUser.setGrantedAuthorities(authorities);
        userRepository.save(newUser);
        return newUser;
    }

    public Optional<SecurityUser> getByUserName(String username) {
        var securityUser = userRepository.findBySecurityUserName(username);
        if(securityUser.isEmpty()){
            throw  new UsernameNotFoundException("User does not exists");
        }
        return securityUser;
    }
}
