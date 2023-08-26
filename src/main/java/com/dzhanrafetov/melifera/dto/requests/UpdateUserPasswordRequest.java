package com.dzhanrafetov.melifera.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UpdateUserPasswordRequest {




    @NotBlank
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$",
            message = "Error password format")
    private String oldPassword; // New field for old password
    @NotBlank
    @Pattern(
            // ?= look ahead saying that it is a group in the
            //  . matches any character (except for line terminators)
            //*? matches the previous token between zero and unlimited times,
            // as few times as possible, expanding as needed (lazy)
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$",
            message = "Error password  format")
    private String newPassword;

    public UpdateUserPasswordRequest() {
    }

    public UpdateUserPasswordRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public UpdateUserPasswordRequest(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}








