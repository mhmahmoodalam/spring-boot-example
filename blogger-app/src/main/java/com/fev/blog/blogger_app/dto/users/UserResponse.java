package com.fev.blog.blogger_app.dto.users;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("user")
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String bio;
    private String image;
    private String token;

}
