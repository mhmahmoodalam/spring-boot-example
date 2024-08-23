package com.fev.blog.blogger_app.assembler;


import com.fev.blog.blogger_app.dto.users.UserResponse;
import com.fev.blog.blogger_app.entity.SecurityUser;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public static UserResponse convertUserResponseFrom(SecurityUser user){
        return new UserResponse();
    }
}
