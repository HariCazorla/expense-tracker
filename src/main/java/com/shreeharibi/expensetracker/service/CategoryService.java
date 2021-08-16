package com.shreeharibi.expensetracker.service;

import com.shreeharibi.expensetracker.exceptions.CategoryDuplicateException;
import com.shreeharibi.expensetracker.exceptions.CategoryNotFoundException;
import com.shreeharibi.expensetracker.model.Category;
import com.shreeharibi.expensetracker.category.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addNewCategory(Category category) throws CategoryDuplicateException {
        Optional<Category> _category = categoryRepository.findCategoryByName(category.getName());

        if (_category.isPresent()) {
            logger.error("category " + category.getName() + " already exists...");
            throw new CategoryDuplicateException("Category exists...");
        }
        categoryRepository.save(category);
    }

    public void deleteCategoryByName(String categoryName) throws CategoryNotFoundException {
        Optional<Category> _category = categoryRepository.findCategoryByName(categoryName);

        if (!_category.isPresent()) {
            logger.error("category " + categoryName + " does not exist...");
            throw new CategoryNotFoundException("Category does not exist...");
        }
        System.out.println("Deleting " + categoryName);
        categoryRepository.deleteById(_category.get().getId());
        return;
    }

    public void deleteCategoryById(Long categoryId) throws CategoryNotFoundException {
        Optional<Category> _category = categoryRepository.findById(categoryId);

        if (_category.isPresent()) {
            categoryRepository.deleteById(categoryId);
        }

        logger.error("category id " + categoryId + " does not exist...");
        throw new CategoryNotFoundException("Category does not exist...");
    }

    @Transactional
    public ResponseEntity<Category> updateCategory(String oldCategoryName, Category category) throws CategoryNotFoundException {
        Optional<Category> _category = categoryRepository.findCategoryByName(oldCategoryName);

        if (!_category.isPresent()) {
            logger.error("category " + oldCategoryName + " does not exist...");
            throw new CategoryNotFoundException("Category does not exist...");
        }

        _category.get().setName(category.getName());
        _category.get().setDescription(category.getDescription());
        Category updatedCategory = categoryRepository.save(_category.get());
        return ResponseEntity.ok(updatedCategory);
    }

    public Category getCategoryById(Long categoryId) throws CategoryNotFoundException {
        Optional<Category> _category = categoryRepository.findById(categoryId);

        if (!_category.isPresent()) {
            logger.error("category id " + categoryId + " does not exist...");
            throw new CategoryNotFoundException("Category does not exist...");
        }

        return _category.get();
    }

    public Category getCategoryByName(String categoryName) throws CategoryNotFoundException {
        Optional<Category> _category = categoryRepository.findCategoryByName(categoryName);

        if (!_category.isPresent()) {
            logger.error("category " + categoryName + " does not exist...");
            throw new CategoryNotFoundException("Category does not exist...");
        }

        return _category.get();
    }
}
