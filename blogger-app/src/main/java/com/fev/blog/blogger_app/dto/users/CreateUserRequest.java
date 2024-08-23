package com.fev.blog.blogger_app.dto.users;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName("user")
public class CreateUserRequest {
    @NotNull
    @NotEmpty
    private String firstName;
    private String lastName;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;
    private String bio;
    private String image;

}
