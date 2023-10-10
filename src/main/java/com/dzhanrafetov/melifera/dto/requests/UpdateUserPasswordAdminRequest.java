package com.dzhanrafetov.melifera.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UpdateUserPasswordAdminRequest {


    @NotBlank
    @Pattern(
            // ?= look ahead saying that it is a group in the
            //  . matches any character (except for line terminators)
            //*? matches the previous token between zero and unlimited times,
            // as few times as possible, expanding as needed (lazy)
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$",
            message = "Error password  format")
    private String newPassword;

    public UpdateUserPasswordAdminRequest() {
    }

    public UpdateUserPasswordAdminRequest(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}





