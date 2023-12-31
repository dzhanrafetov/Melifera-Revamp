package com.dzhanrafetov.melifera.dto.converters;


import com.dzhanrafetov.melifera.dto.CategoryDto;
import com.dzhanrafetov.melifera.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter {

    public CategoryDto convert(Category from) {
        return new CategoryDto(
                from.getId(),
                from.getCategoryName());

    }
    public List<CategoryDto> convert(List<Category> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }


}
