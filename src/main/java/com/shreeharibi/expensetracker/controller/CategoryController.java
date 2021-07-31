package com.shreeharibi.expensetracker.controller;

import com.shreeharibi.expensetracker.model.Category;
import com.shreeharibi.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getCategoryByIdorName(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {
        List<Category> result = new ArrayList<Category>();
        if ((id == null) && (name == null)) {
            result = categoryService.getCategories();
        }
        else if (name == null) {
            result.add(categoryService.getCategoryById(id));
        }
        else {
            result.add(categoryService.getCategoryByName(name));
        }
        return result;
    }

    @PostMapping
    public void addNewCategory(@RequestBody Category category) {
        categoryService.addNewCategory(category);
    }

    @DeleteMapping
    public void deleteCategoryByName(@RequestBody Map<String, List<String>> categoryList) {
        List<String> categories = categoryList.get("category");
        for (String category : categories) {
            categoryService.deleteCategoryByName(category);
        }
    }

    @DeleteMapping(path = "{categoryId}")
    public void deleteCategoryById(
            @PathVariable("categoryId") Long categoryId
    ) {
        categoryService.deleteCategoryById(categoryId);
    }

    @PutMapping(path = "{oldCategoryName}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable("oldCategoryName") String oldCategoryName,
            @RequestBody Category category
    ) {
        return categoryService.updateCategory(oldCategoryName, category);
    }
}
