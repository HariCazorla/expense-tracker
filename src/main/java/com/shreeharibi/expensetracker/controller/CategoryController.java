package com.shreeharibi.expensetracker.controller;

import com.shreeharibi.expensetracker.exceptions.CategoryDuplicateException;
import com.shreeharibi.expensetracker.exceptions.CategoryNotFoundException;
import com.shreeharibi.expensetracker.model.Category;
import com.shreeharibi.expensetracker.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getCategoryByIdorName(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {
        List<Category> result = new ArrayList<Category>();
        try {
            if ((id == null) && (name == null)) {
                logger.info("Getting list of all categories...");
                result = categoryService.getCategories();
            }
            else if (name == null) {
                logger.info("Getting category by id "+ id +"...");
                result.add(categoryService.getCategoryById(id));
            }
            else {
                logger.info("Getting category by name "+ name +"...");
                result.add(categoryService.getCategoryByName(name));
            }
        } catch (CategoryNotFoundException e) {
            logger.error("Failed to fetch category information...");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return result;
    }

    @PostMapping
    public void addNewCategory(@RequestBody Category category) {
        try {
            logger.info("Adding new category "+ category +"...");
            categoryService.addNewCategory(category);
        } catch (CategoryDuplicateException e) {
            logger.error("Failed to add new category...");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping
    public void deleteCategoryByName(@RequestBody Map<String, List<String>> categoryList) {
        List<String> categories = categoryList.get("category");
        boolean status = false;
        for (String category : categories) {
            try {
                logger.info("Deleting category "+ category +"...");
                categoryService.deleteCategoryByName(category);
            } catch (CategoryNotFoundException e) {
                status = true;
                continue;
            }
        }
        if (status) {
            logger.error("Failed to delete some category...");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "{categoryId}")
    public void deleteCategoryById(
            @PathVariable("categoryId") Long categoryId
    ) {
        try {
            logger.info("Deleting category id "+ categoryId +"...");
            categoryService.deleteCategoryById(categoryId);
        } catch (CategoryNotFoundException e) {
            logger.error("Failed to delete category...");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping(path = "{oldCategoryName}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable("oldCategoryName") String oldCategoryName,
            @RequestBody Category category
    ) {
        try {
            logger.info("update category name "+ oldCategoryName +"...");
            return categoryService.updateCategory(oldCategoryName, category);
        } catch (CategoryNotFoundException e) {
            logger.error("Failed to update category...");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
