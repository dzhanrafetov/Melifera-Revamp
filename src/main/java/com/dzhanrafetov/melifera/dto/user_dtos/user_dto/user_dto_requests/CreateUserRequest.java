package com.dzhanrafetov.melifera.dto.user_dtos.user_dto.user_dto_requests;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CreateUserRequest {
    @NotBlank
    @Pattern(
            regexp = "(^[a-zA-Z0-9._-]{3,}$)",
            message = "Error username  format")
    private String username;

   @NotBlank
   @Pattern(
           // ?= look ahead saying that it is a group in the
            //  . matches any character (except for line terminators)
           //*? matches the previous token between zero and unlimited times,
           // as few times as possible, expanding as needed (lazy)
           regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$",
           message = "Error password  format")
   private String password;
   @NotBlank
   @Email
    private String mail;


    public CreateUserRequest() {
    }

    public CreateUserRequest(String username, String password,String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
