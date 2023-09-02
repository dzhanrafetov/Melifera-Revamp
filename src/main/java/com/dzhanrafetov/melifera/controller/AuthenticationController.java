package com.dzhanrafetov.melifera.controller;


import com.dzhanrafetov.melifera.dto.AuthenticationDto;
import com.dzhanrafetov.melifera.dto.requests.AuthenticationRequest;
import com.dzhanrafetov.melifera.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationDto> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        String jwtToken = authenticationService.authenticateUser(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationDto(jwtToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok("Logged out successfully");
    }

}
