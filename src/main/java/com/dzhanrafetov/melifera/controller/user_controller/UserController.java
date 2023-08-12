package com.dzhanrafetov.melifera.controller.user_controller;


import com.dzhanrafetov.melifera.dto.user_dtos.user_dto.UserDto;
import com.dzhanrafetov.melifera.dto.user_dtos.user_dto.user_dto_requests.CreateUserRequest;
import com.dzhanrafetov.melifera.service.user_service.UserService;
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


    @GetMapping("admin/getUserByMail/{mail}")
    public ResponseEntity<UserDto> getUserByMail(@PathVariable String mail){
        return ResponseEntity.ok(userService.getUserByMail(mail));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));

    }

    @PatchMapping("admin/deactivateUserById/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();

    }

    @PatchMapping("admin/activateUserById/{id}")
    public ResponseEntity<Void> activateUser(@PathVariable("id") Long id) {
        userService.activateUser(id);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("admin/deleteUserById/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();

    }
    @GetMapping("admin/isUserIdExistById/{id}")
    public ResponseEntity<Boolean> isUserIdExist(@PathVariable Long id){
        return ResponseEntity.ok(userService.isUserIdExist(id));
    }

    @GetMapping("confirm")
    public String confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }

    }