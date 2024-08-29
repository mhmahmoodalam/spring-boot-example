package com.fev.blog.blogger_app.authentication.service;

import com.fev.blog.blogger_app.authentication.repository.SecurityUserDetailsRepository;
import com.fev.blog.blogger_app.dto.users.UserLoginRequest;
import com.fev.blog.blogger_app.dto.users.UserLoginResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService {

    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private SecurityUserDetailsRepository securityUserDetailsRepository;

    @Transactional
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        var authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.getEmail(),loginRequest.getPassword());
        var authentication = authenticationManager.authenticate(authenticationRequest);
        if(authentication.isAuthenticated()){
            var securityUser = securityUserDetailsRepository.findBySecurityUserName(authentication.getName());
            var extraClaims = new HashMap<String, Object>();
            extraClaims.put("username", authentication.getName());
            extraClaims.put("authorities", populateAuthorities(authentication));
            var token = jwtService.generateToken(extraClaims, (UserDetails) authentication.getPrincipal());
            securityUser.ifPresent((user) -> {
                user.setToken(token);
                securityUserDetailsRepository.saveAndFlush(user);
            });
            return new UserLoginResponse(HttpStatus.OK.name(), token);
        }else{
            return new UserLoginResponse(HttpStatus.BAD_REQUEST.name(), null);
        }
    }



    private String populateAuthorities(Authentication authentication) {
        Set<String> authoritiesSet = new HashSet<>();
        authentication.getAuthorities().forEach(auth -> authoritiesSet.add(auth.getAuthority()));
        return String.join(",",authoritiesSet);
    }
}
