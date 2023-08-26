package com.dzhanrafetov.melifera.controller;


import com.dzhanrafetov.melifera.dto.UserDto;
import com.dzhanrafetov.melifera.dto.requests.CreateUserRequest;
import com.dzhanrafetov.melifera.dto.requests.UpdateUserPasswordRequest;
import com.dzhanrafetov.melifera.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("admin/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));

    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.ok(userService.getUser());

    }

    @PutMapping("updateUserPasswordById/{id}")
    public ResponseEntity<UserDto> updateUserPassword
            (@PathVariable Long id,
             @Valid @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest) {
        return ResponseEntity.ok
                (userService.updateUserPassword(id, updateUserPasswordRequest));
    }

    @PostMapping("/send-password-reset-link")
    public ResponseEntity<String> sendPasswordResetLink(@RequestParam String mail) {
        String result = userService.sendPasswordResetLink(mail);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<UserDto> resetPassword(@RequestParam String token, @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest) {
        return ResponseEntity.ok(userService.resetPassword(token, updateUserPasswordRequest));
    }


    @GetMapping("admin/getUserByMail/{mail}")
    public ResponseEntity<UserDto> getUserByMail(@PathVariable String mail) {
        return ResponseEntity.ok(userService.getUserByMail(mail));
    }

    @GetMapping("admin/getActiveUsers")
    public ResponseEntity<List<UserDto>> getActiveUsers() {
        return ResponseEntity.ok(userService.getActiveUsers());
    }

    @GetMapping("admin/getInActiveUsers")
    public ResponseEntity<List<UserDto>> getInActiveUsers() {
        return ResponseEntity.ok(userService.getInActiveUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));

    }

    @PatchMapping("admin/deactivateUserById/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build(); // Use 204 No Content

    }

    @PatchMapping("admin/activateUserById/{id}")
    public ResponseEntity<Void> activateUser(@PathVariable("id") Long id) {
        userService.activateUser(id);
        return ResponseEntity.noContent().build(); // Use 204 No Content

    }

    @PutMapping("updateUserPasswordWithOldPassword/{id}")
    public ResponseEntity<UserDto> updateUserPasswordWithOldPassword(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserPasswordRequest request) {

        return ResponseEntity.ok(userService.updateUserPasswordWithOldPassword(id, request));
    }


    @DeleteMapping("admin/deleteUserById/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("admin/isUserIdExistById/{id}")
    public ResponseEntity<Boolean> isUserIdExist(@PathVariable Long id) {
        return ResponseEntity.ok(userService.isUserIdExist(id));
    }

    @GetMapping("confirm")
    public String confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }

    @PostMapping("resend-confirmation")
    public String resendConfirmationToken(@RequestParam String mail) {
        return userService.resendToken(mail);
    }
}