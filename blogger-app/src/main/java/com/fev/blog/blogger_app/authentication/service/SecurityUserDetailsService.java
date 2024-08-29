package com.fev.blog.blogger_app.authentication.service;

import com.fev.blog.blogger_app.authentication.models.SecurityUserModel;
import com.fev.blog.blogger_app.authentication.repository.SecurityUserDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private SecurityUserDetailsRepository securityUserDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var securityUser = securityUserDetailsRepository.findBySecurityUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new SecurityUserModel(securityUser);
    }
}
