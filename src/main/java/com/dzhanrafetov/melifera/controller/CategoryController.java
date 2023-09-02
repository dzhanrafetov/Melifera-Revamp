package com.dzhanrafetov.melifera.controller;



import com.dzhanrafetov.melifera.dto.CategoryDto;
import com.dzhanrafetov.melifera.dto.requests.CreateCategoryRequest;
import com.dzhanrafetov.melifera.dto.requests.UpdateCategoryRequest;
import com.dzhanrafetov.melifera.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/category")

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("admin/createCategory")
    public ResponseEntity<CategoryDto> createCategory
            (@Valid @RequestBody CreateCategoryRequest categoryRequest) {
        return ResponseEntity.ok
                (categoryService.
                        create(categoryRequest));

    }


    @GetMapping("admin/getCategoryByName/{name}")
    public List<CategoryDto> getCategoryById(@PathVariable String name){
        return
                (categoryService.
                        findCategoryByName(name));

    }
    @GetMapping("admin/getCategoryById/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok
                (categoryService.
                        getCategoryById(id));

    }

    @GetMapping("categories/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());

    }

//    @GetMapping("categories/")
//    public String getAllCategories(Model model) {
//        List<CategoryDto> categories = categoryService.getAllCategories();
//        model.addAttribute("categories", categories);
//        return "index";
//    }

    @PutMapping("admin/updateCategoryById/{id}")
    public ResponseEntity<CategoryDto>
    updateCategory(@PathVariable Long id,
                   @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return ResponseEntity.ok
                (categoryService.
                        updateCategory(id,updateCategoryRequest));

    }

    @DeleteMapping("admin/deleteCategoryById/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();

    }


}
