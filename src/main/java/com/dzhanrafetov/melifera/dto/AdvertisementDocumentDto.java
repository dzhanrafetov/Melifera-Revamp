package com.dzhanrafetov.melifera.dto;

import java.time.LocalDateTime;

public class AdvertisementDocumentDto {

    private String id;
    private String title;
    private String description;
    private Double price;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private Boolean isArchived;
    private Long categoryId;
    private Long userId;

    public AdvertisementDocumentDto() {
    }

    public AdvertisementDocumentDto(String id, String title, String description, Double price, LocalDateTime creationDate, LocalDateTime lastModifiedDate, Boolean isArchived, Long categoryId, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.isArchived = isArchived;
        this.categoryId = categoryId;
        this.userId = userId;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
