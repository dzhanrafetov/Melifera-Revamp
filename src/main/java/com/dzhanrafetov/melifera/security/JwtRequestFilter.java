package com.dzhanrafetov.melifera.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtils;
    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtUserDetailsService userDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtils, RedisTemplate<String, Object> redisTemplate, JwtUserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.redisTemplate = redisTemplate;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtils.getUserNameFromJwtToken(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Check if the JWT token exists in Redis
            if (redisContains(username, jwt)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtils.validateJwtToken(jwt) && userDetails != null) {
                    System.out.println(userDetails.getPassword());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        chain.doFilter(request, response);
    }


    private boolean redisContains(String username, String jwt) {
        try {
            // Get the stored JWT token from Redis for the given username
            String storedJwt = (String) redisTemplate.opsForValue().get(username);

            // Check if the stored JWT token matches the incoming JWT
            return storedJwt != null && storedJwt.equals(jwt);
        } catch (Exception e) {
            // Handle any exceptions that may occur during Redis operations
            // You can log the error or handle it according to your application's requirements
            e.printStackTrace();
            return false; // Return false to indicate an error
        }
    }

}