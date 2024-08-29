package com.fev.blog.blogger_app.assembler;


import com.fev.blog.blogger_app.dto.users.UserResponse;
import com.fev.blog.blogger_app.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public static UserResponse convertUserResponseFrom(Profile user) {
        return new UserResponse();
    }
}
