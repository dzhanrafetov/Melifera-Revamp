package com.dzhanrafetov.melifera.dto.advertisement_dtos.category_dto;

public class CategoryDto {
    private final Long  id;
    private final String categoryName;

    public CategoryDto(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }


    public Long getId() {
        return id;
    }
}
