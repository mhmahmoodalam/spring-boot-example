package com.fev.blog.blogger_app.assembler;

import com.fev.blog.blogger_app.dto.profiles.ProfileResponse;
import com.fev.blog.blogger_app.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileAssembler {

    public static ProfileResponse convertProfileResponseFrom(Profile user) {
        return new ProfileResponse();
    }
}
