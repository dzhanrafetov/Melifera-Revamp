package com.dzhanrafetov.melifera.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AdvertisementDto {
    private String id;
    private final String title;
    private final String description;
    private final BigDecimal price;
    private final String categoryName;
    private final LocalDateTime creationDate;
    private final LocalDateTime lastModifiedDate;
    private final Boolean isArchived;
    private final Long createdByUserId;
    private final List<ImageDto> images;





    public AdvertisementDto(String id,
                            String title,
                            String description,
                            BigDecimal price,
                            LocalDateTime creationDate,
                            LocalDateTime lastModifiedDate,
                            Long userId,
                            String categoryName,
                            Boolean isArchived,
                            List<ImageDto> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createdByUserId = userId;
        this.categoryName = categoryName;
        this.isArchived = isArchived;
        this.images = images;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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



    public Long getCreatedByUserId() {
        return createdByUserId;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public Boolean getArchived() {
        return isArchived;
    }
}
