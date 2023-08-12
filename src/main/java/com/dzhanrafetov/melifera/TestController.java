package com.dzhanrafetov.melifera;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class TestController {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/message")
    @ResponseBody // Add this annotation to return a JSON response
    public String welcome() {
        System.out.println("Message from application.propertiess: " + message);
        return "Welcome to our application. MessageeeS: " + message;
    }
}
