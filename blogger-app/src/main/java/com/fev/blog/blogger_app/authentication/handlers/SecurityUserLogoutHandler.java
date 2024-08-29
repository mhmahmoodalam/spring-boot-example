package com.fev.blog.blogger_app.authentication.handlers;

import com.fev.blog.blogger_app.authentication.repository.SecurityUserDetailsRepository;
import com.fev.blog.blogger_app.authentication.service.AuthService;
import com.fev.blog.blogger_app.authentication.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityUserLogoutHandler implements LogoutHandler {


    private SecurityUserDetailsRepository securityUserDetailsRepository;
    private JwtService jwtService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        var authHeader = request.getHeader("Authorization");
        final String jwt = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(jwt.trim());
        clearAccessToken(userEmail);
    }

    @Transactional
    private void clearAccessToken(String securityUserName){
        var securityUser = securityUserDetailsRepository.findBySecurityUserName(securityUserName);
        securityUser.ifPresent((user) -> {
            user.setToken(null);
            securityUserDetailsRepository.saveAndFlush(user);
        });
    }
}
