package com.dzhanrafetov.melifera.service;

import com.dzhanrafetov.melifera.dto.requests.AuthenticationRequest;
import com.dzhanrafetov.melifera.security.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, Object> redisTemplate;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, RedisTemplate<String, Object> redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.redisTemplate = redisTemplate;
    }

    public void logout(HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();

        // Remove the token from the user's session in Redis to log them out
        redisTemplate.delete(username);
    }


    public String authenticateUser(AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        // Authenticate the user using the provided username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Check if a token already exists for this user in Redis
        String existingToken = (String) redisTemplate.opsForValue().get(username);

        if (existingToken != null && jwtUtil.validateJwtToken(existingToken)) {
            // If a valid token exists, return it
            return existingToken;
        }

        // Generate a new JWT token
        String jwtToken = jwtUtil.generateJwtToken(authentication.getName());

        // Store the JWT token in Redis for session management
        redisTemplate.opsForValue().set(username, jwtToken, jwtUtil.getJwtExpirationMs(), TimeUnit.MILLISECONDS);

        return jwtToken;
    }


}
