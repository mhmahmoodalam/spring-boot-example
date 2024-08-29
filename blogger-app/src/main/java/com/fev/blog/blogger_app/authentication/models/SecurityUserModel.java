package com.fev.blog.blogger_app.authentication.models;

import com.fev.blog.blogger_app.authentication.entity.SecurityUser;
import com.fev.blog.blogger_app.entity.Profile;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class SecurityUserModel implements UserDetails {

    private SecurityUser securityUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return securityUser.getGrantedAuthorities()
                .stream().map(SecurityAuthorityModel::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return securityUser.getPassword();
    }

    @Override
    public String getUsername() {
        return securityUser.getSecurityUserName();
    }

    public String getToken() {
        return securityUser.getToken();
    }

    public Profile getProfile() {
        return securityUser.getProfile();
    }
}
