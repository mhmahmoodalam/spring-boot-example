package com.fev.blog.blogger_app.authentication.config;

import com.fev.blog.blogger_app.authentication.filters.JWTAuthenticationFilter;
import com.fev.blog.blogger_app.authentication.handlers.SecurityUserLogoutHandler;
import com.fev.blog.blogger_app.authentication.repository.SecurityUserDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class  SecurityConfig {

    private JWTAuthenticationFilter jwtAuthenticationFilter;
    private SecurityUserLogoutHandler securityUserLogoutHandler;
    private SecurityUserDetailsRepository securityUserDetailsRepository;

    private static final String[] PUBLIC_READ_ENDPOINTS = {
            "/tags",
            "/profiles", "/profiles/*",
            "/articles", "/articles/*", "/articles/**",
            "/webjars/**", "/**", "/swagger-ui*/*swagger-initializer.js", "/swagger-ui*/**"
    };

    private static final String[] PUBLIC_WRITE_ENDPOINTS = {
            "/users/register", "/auth/login"
    };



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(
                new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.COOKIES));
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers( HttpMethod.GET,PUBLIC_READ_ENDPOINTS)
                                .permitAll()
                                .requestMatchers(PUBLIC_WRITE_ENDPOINTS)
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .logout((logout) -> logout
                        .logoutUrl("/auth/logout")
                        .addLogoutHandler(securityUserLogoutHandler)
                        //.addLogoutHandler(clearSiteData)
                        //.addLogoutHandler(new SecurityContextLogoutHandler())
                        .logoutSuccessHandler(
                                new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                )
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        var daoAuthenticatorProvider = new DaoAuthenticationProvider(passwordEncoder());
        daoAuthenticatorProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticatorProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
