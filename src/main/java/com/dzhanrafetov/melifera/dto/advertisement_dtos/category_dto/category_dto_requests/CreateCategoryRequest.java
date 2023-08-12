package com.dzhanrafetov.melifera.dto.advertisement_dtos.category_dto.category_dto_requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CreateCategoryRequest {
//
    @NotBlank
    @Pattern(
            regexp = "(^[A-Z][a-z-\\s,]{3,40}+)",
            message = "Error Category name format ," +
                    " Category name must contain " +
                    "min. 4 characters and max 40 characters and" +
                    " first letter must be upper case")
    private String categoryName;

    public CreateCategoryRequest() {
    }

    public CreateCategoryRequest(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
