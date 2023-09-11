package com.dzhanrafetov.melifera.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "advertisement")
public class AdvertisementDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Double, name = "price")
    private Double price;

    @Field(type = FieldType.Date, name = "creation_date")
    private LocalDateTime creationDate;

    @Field(type = FieldType.Date, name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Field(type = FieldType.Boolean, name = "is_archived")
    private Boolean isArchived;

    @Field(type = FieldType.Long, name = "category_id")
    private Long categoryId;

    @Field(type = FieldType.Long, name = "user_id")
    private Long userId;

    // Constructors, getters, and setters as needed

    public AdvertisementDocument(String id, String title, String description, Double price, LocalDateTime creationDate, LocalDateTime lastModifiedDate, Boolean isArchived, Long categoryId, Long userId) {
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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getUserId() {
        return userId;
    }
// Other fields and methods as needed
}
