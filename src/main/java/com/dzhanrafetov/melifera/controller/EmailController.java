package com.dzhanrafetov.melifera.controller;

import com.dzhanrafetov.melifera.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/email")
public class EmailController {
    private final EmailService emailService;


    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/sendmail")
    public String sendEmail(){
        this.emailService.sendMail(
                "can.33@abv.bg",
                "sda",
                "asd"
        );
        return  "Message sent";
    }

}