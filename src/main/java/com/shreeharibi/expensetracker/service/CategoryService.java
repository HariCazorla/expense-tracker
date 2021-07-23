package com.shreeharibi.expensetracker.service;

import com.shreeharibi.expensetracker.category.Category;
import com.shreeharibi.expensetracker.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addNewCategory(Category category) {
        Optional<Category> _category = categoryRepository.findCategoryByName(category.getName());

        if (_category.isPresent()) {
            throw new IllegalStateException("Category exists...");
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(String categoryName) {
        Optional<Category> _category = categoryRepository.findCategoryByName(categoryName);

        if (!_category.isPresent()) {
            System.out.println("Category does not exist...");
            return;
        }
        System.out.println("Deleting " + categoryName);
        categoryRepository.deleteById(_category.get().getId());
        return;
    }
}