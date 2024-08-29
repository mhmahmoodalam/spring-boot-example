package com.fev.blog.blogger_app.authentication.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "com.fev.api.security")
@Validated
@Getter
@Setter
public class AuthProperties {

    @NotEmpty
    @NotNull
    private String secretKey;

    @Min(2000)
    private long expirationTime;
}
