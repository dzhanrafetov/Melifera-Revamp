package com.dzhanrafetov.melifera.dto.user_dtos.user_dto;



import com.dzhanrafetov.melifera.dto.advertisement_dtos.advertisement_dto.AdvertisementDto;
import com.dzhanrafetov.melifera.dto.user_dtos.userdetails_dto.UserDetailsDto;
import com.dzhanrafetov.melifera.enumeration.Role;

import java.time.LocalDateTime;
import java.util.List;

public class UserDto {
    private final Long id;
    private final String username;
    private final String password;//moje da ne vrushta parola
    private final Role role;


    private final String mail;
    private final LocalDateTime creationTime;
    private final Boolean isActive;
    private final List<UserDetailsDto> userDetails;
    private final List<AdvertisementDto> advertisements;

    public UserDto(Long id,
                   String username,
                   String password,
                   Role role,
                   String mail,
                   LocalDateTime creationTime,
                   Boolean isActive,
                   List<UserDetailsDto> userDetails,
                   List<AdvertisementDto> advertisements) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role=role;
        this.mail = mail;
        this.creationTime = creationTime;
        this.isActive = isActive;
        this.userDetails = userDetails;
        this.advertisements = advertisements;
    }


    public Boolean getActive() {
        return isActive;
    }

    public String getMail() {
        return mail;
    }


    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public List<UserDetailsDto> getUserDetails() {
        return userDetails;
    }

    public List<AdvertisementDto> getAdvertisements() {
        return advertisements;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Role getRole() {
        return role;
    }
}

