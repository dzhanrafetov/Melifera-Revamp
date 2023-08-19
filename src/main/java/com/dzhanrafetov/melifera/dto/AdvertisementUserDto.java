package com.dzhanrafetov.melifera.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AdvertisementUserDto {
    private final String advertisementId;
    private final String title;
    private final String description;
    private final String categoryName;
    private final BigDecimal price;
    private final LocalDateTime creationDate;
    private final LocalDateTime lastModifiedDate;
    private final String username;



    private final  String mail;
    private final List<ImageDto> images;

    private final List<AdvertisementDto> advertisementsAddedByUser;

    private  List<UserDetailsDto> userdetails;



    public AdvertisementUserDto(String advertisementId,
                                String title,
                                String description,
                                String categoryName,
                                BigDecimal price,
                                LocalDateTime creationDate,
                                LocalDateTime lastModifiedDate,
                                String username,
                                String mail,
                                List<ImageDto> images,
                                List<AdvertisementDto> advertisementsAddedByUser

                                ) {
        this.advertisementId = advertisementId;
        this.title = title;
        this.description = description;
        this.categoryName = categoryName;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.username = username;
        this.mail = mail;
        this.images = images;
        this.advertisementsAddedByUser = advertisementsAddedByUser;

    }


    public AdvertisementUserDto(String advertisementId,
                                String title,
                                String description,
                                String categoryName,
                                BigDecimal price,
                                LocalDateTime creationDate,
                                LocalDateTime lastModifiedDate,
                                String username,
                                String mail,
                                List<ImageDto> images,
                                List<AdvertisementDto> advertisements,
                                List<UserDetailsDto> userdetails) {
        this.advertisementId = advertisementId;
        this.title = title;
        this.description = description;
        this.categoryName = categoryName;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.username = username;
        this.mail = mail;
        this.images = images;
        this.advertisementsAddedByUser = advertisements;
        this.userdetails = userdetails;


    }


    public String getAdvertisementId() {
        return advertisementId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }



    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public List<AdvertisementDto> getAdvertisementsAddedByUser() {
        return advertisementsAddedByUser;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public List<UserDetailsDto> getUserdetails() {
        return userdetails;
    }


}
