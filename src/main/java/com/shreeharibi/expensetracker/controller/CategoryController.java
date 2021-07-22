package com.shreeharibi.expensetracker.controller;

import com.shreeharibi.expensetracker.category.Category;
import com.shreeharibi.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public void addNewCategory(@RequestBody Category category) {
        categoryService.addNewCategory(category);
    }
}
