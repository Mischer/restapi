package com.mk.restapi.security;

import com.mk.restapi.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, @Lazy UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    // Filter to authenticate requests using JWT tokens
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Get JWT token from request header
            String jwt = getJwtFromRequest(request);

            // Validate token
            if (jwt != null && tokenProvider.validateToken(jwt)) {
                // Get username from token
                String username = tokenProvider.getUsernameFromToken(jwt);

                // Load user details
                UserDetails userDetails = userService.loadByUsername(username);

                // Create authentication token
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Set authentication details
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set authentication in context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            //TODO Log authentication errors
        }

        // Continue filter chain
        filterChain.doFilter(request, response);
    }

    // Extract JWT token from Authorization header
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        // Check if header contains Bearer token
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Extract token
            return bearerToken.substring(7);
        }
        return null;
    }
}