package com.fev.blog.blogger_app.authentication.filters;

import com.fev.blog.blogger_app.authentication.models.SecurityUserModel;
import com.fev.blog.blogger_app.authentication.service.JwtService;
import com.fev.blog.blogger_app.authentication.service.SecurityUserDetailsService;
import com.fev.blog.blogger_app.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private JwtService jwtService;
    private SecurityUserDetailsService securityUserDetailsService;
    public static final String JWT_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(JWT_HEADER);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt.trim());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (userEmail != null && authentication == null) {
                SecurityUserModel userDetails = (SecurityUserModel) this.securityUserDetailsService.loadUserByUsername(userEmail);

                if (userDetails.getToken()!=null && jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }else {
                    throw new InsufficientAuthenticationException("Token expired, Please login again.");
                }
            }

            filterChain.doFilter(request, response);
    }


}
