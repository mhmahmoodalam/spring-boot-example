package com.fev.blog.blogger_app.authentication.models;

import com.fev.blog.blogger_app.authentication.entity.Authority;

public enum Roles {

    ADMIN("ADMIN"),
    MODERATOR("MODERATOR"),
    READER("READER");

    private final String roleName;

    Roles(String name) {
        this.roleName = name;
    }

    public Authority toAuthority() {
        var authority = new Authority();
        authority.setName(this.roleName);
        return authority;
    }
}
