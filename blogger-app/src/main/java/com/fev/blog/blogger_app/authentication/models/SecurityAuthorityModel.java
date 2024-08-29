package com.fev.blog.blogger_app.authentication.models;

import com.fev.blog.blogger_app.authentication.entity.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthorityModel implements GrantedAuthority {

    private Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
