package com.dzhanrafetov.melifera.controller.user_controller;


import com.dzhanrafetov.melifera.dto.user_dtos.userdetails_dto.UserDetailsDto;
import com.dzhanrafetov.melifera.dto.user_dtos.userdetails_dto.userdetails_dto_requests.CreateUserDetailsRequest;
import com.dzhanrafetov.melifera.dto.user_dtos.userdetails_dto.userdetails_dto_requests.UpdateUserDetailsRequest;
import com.dzhanrafetov.melifera.service.user_service.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/v1/userdetails")
@RestController
public class UserDetailsController {
    private final UserDetailsService userDetailsService;
//POST VS PUT

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }



    @PostMapping
    public ResponseEntity<UserDetailsDto> createUserDetails(
            @Valid @RequestBody CreateUserDetailsRequest request) {
        return ResponseEntity.ok(userDetailsService.createUserDetails(request));

    }


    @PutMapping("updateUserDetailsByUserId/{id}")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable Long id,
                                                            @Valid @RequestBody UpdateUserDetailsRequest request) {
        return ResponseEntity.ok(userDetailsService.updateUserDetails(id, request));

    }
    @DeleteMapping("deleteUserDetailsById/{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id){
        userDetailsService.deleteUserDetails(id);
        return ResponseEntity.ok().build();
    }
}