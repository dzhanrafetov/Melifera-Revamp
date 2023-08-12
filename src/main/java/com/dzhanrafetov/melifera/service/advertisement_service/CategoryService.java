package com.dzhanrafetov.melifera.service.advertisement_service;


import com.dzhanrafetov.melifera.dto.advertisement_dtos.category_dto.CategoryDto;
import com.dzhanrafetov.melifera.dto.advertisement_dtos.category_dto.category_dto_converters.CategoryDtoConverter;
import com.dzhanrafetov.melifera.dto.advertisement_dtos.category_dto.category_dto_requests.CreateCategoryRequest;
import com.dzhanrafetov.melifera.dto.advertisement_dtos.category_dto.category_dto_requests.UpdateCategoryRequest;
import com.dzhanrafetov.melifera.exception.NotFoundException;
import com.dzhanrafetov.melifera.model.advertisement_model.Category;
import com.dzhanrafetov.melifera.repository.advertisement_repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private  final CategoryRepository repository;
    private final CategoryDtoConverter converter;


    public CategoryService(CategoryRepository repository, CategoryDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }
    public CategoryDto create(CreateCategoryRequest request) {


        Category category =
                new Category(
                        request.getCategoryName());

        return converter.convert(repository.save(category));
    }
    public CategoryDto getCategoryById(Long id) {
        Category category = findCategoryById(id);
        return converter.convert(category);
    }

    public Category findCategoryById(Long id) {
       return repository.findById(id).orElseThrow(() -> new NotFoundException("Category couldn't found by id:  " + id));
        }

    public List<CategoryDto> findCategoryByName(String categoryName) {
        return converter.convert(repository.findCategoryByCategoryName(categoryName));
    }


    public CategoryDto updateCategory(Long id,
                                      UpdateCategoryRequest request) {
        Category category = findCategoryById(id);

        Category updatedCategory =
                new Category(
                        category.getId(),
                        request.getCategoryName());

        return converter.convert(repository.save(updatedCategory));
    }

    public List<CategoryDto> getAllCategories() {
        return converter.convert(repository.findAll());

    }

    public CategoryDto deleteCategory(Long id) {
        findCategoryById(id);
        repository.deleteById(id);
        return null;
    }
}


