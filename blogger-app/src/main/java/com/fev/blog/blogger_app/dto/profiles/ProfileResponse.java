package com.fev.blog.blogger_app.dto.profiles;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("profile")
public class ProfileResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String bio;
    private String image;
    private boolean following;
}
