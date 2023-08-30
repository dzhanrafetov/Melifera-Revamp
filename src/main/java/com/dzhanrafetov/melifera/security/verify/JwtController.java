package com.dzhanrafetov.melifera.security.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private JwtVerifier jwtVerifier;

    @GetMapping("/generateToken")
    public String generateToken(@RequestParam String username) {
        return jwtGenerator.generateJwtToken(username);
    }

    @GetMapping("/verifyToken")
    public String verifyToken(@RequestParam String token) {
        jwtVerifier.verifyJwtToken(token);
        return "Token verified.";
    }
}
