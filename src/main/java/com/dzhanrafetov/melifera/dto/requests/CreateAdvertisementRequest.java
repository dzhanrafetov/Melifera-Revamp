package com.dzhanrafetov.melifera.dto.requests;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class CreateAdvertisementRequest {
    @NotBlank
    //[A-Za-z0-9-\s]{10,}+
    @Pattern(
            regexp = "([A-Za-z0-9-\\s]{10,}+)",
            message = "Error Advertisement title format")
    private String title;

    @NotBlank
    @Pattern(
            regexp = "([A-Za-z0-9-\\s]{10,}+)",
            message = "Error Advertisement description format")
    private String description;

    @NotNull
    @DecimalMin("1")
    private BigDecimal price;

    //private status used,new enumeration
    //delivery_taking buyer,seller,one_to_one  enumeration

//    private Long userId;
    private Long categoryId;

    public CreateAdvertisementRequest() {
    }

    public CreateAdvertisementRequest(String title, String description, BigDecimal price,  Long categoryId) {
        this.title = title;
        this.description = description;
        this.price = price;
//        this.userId = userId;
        this.categoryId = categoryId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


}
