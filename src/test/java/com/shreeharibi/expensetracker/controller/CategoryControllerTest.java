package com.shreeharibi.expensetracker.controller;

import com.shreeharibi.expensetracker.exceptions.CategoryNotFoundException;
import com.shreeharibi.expensetracker.model.Category;
import com.shreeharibi.expensetracker.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService service;

    @InjectMocks
    private CategoryController controller;

    @Test
    void testGetCategoryByIdorNameWithNullParameters() {
        //arrange
        List<Category> categoryList = List.of(
                new Category(
                        1L,
                        "others",
                        "default category"
                ),
                new Category(
                        2L,
                        "groceries",
                        "You can add groceries here"
                )
        );
        when(service.getCategories()).thenReturn(categoryList);

        //act
        List<Category> result = controller.getCategoryByIdorName(null, null);

        //assert
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(List.class);
        assertThat(result.size()).isEqualTo(categoryList.size());
        assertThat(result).isEqualTo(categoryList);
    }

    @Test
    void testGetCategoryByIdorNameWithIdAsNull() throws CategoryNotFoundException {
        //arrange
        Category category = new Category(
                1L,
                "others",
                "default category"
        );
        when(service.getCategoryByName(anyString())).thenReturn(category);
        List<Category> expectedResult = List.of(category);

        //act
        List<Category> actualResult = controller.getCategoryByIdorName(null, anyString());

        //assert
        assertThat(expectedResult).isNotNull();
        assertThat(expectedResult).isInstanceOf(List.class);
        assertThat(expectedResult.size()).isEqualTo(expectedResult.size());
        assertThat(expectedResult).isEqualTo(expectedResult);
    }

    @Test
    void testGetCategoryByIdorNameWithNameAsNull() throws CategoryNotFoundException {
        //arrange
        Category category = new Category(
                1L,
                "others",
                "default category"
        );
        when(service.getCategoryById(any(Long.class))).thenReturn(category);
        List<Category> expectedResult = List.of(category);

        //act
        List<Category> actualResult = controller.getCategoryByIdorName(1L, null);

        //assert
        assertThat(expectedResult).isNotNull();
        assertThat(expectedResult).isInstanceOf(List.class);
        assertThat(expectedResult.size()).isEqualTo(expectedResult.size());
        assertThat(expectedResult).isEqualTo(expectedResult);
    }

    @Test
    void testGetCategoryByIdThrowsException() throws CategoryNotFoundException {
        //arrange
        when(service.getCategoryById(any(Long.class))).thenThrow(CategoryNotFoundException.class);

        //act && assert
        assertThatThrownBy(() -> {
            List<Category> actualResult = controller.getCategoryByIdorName(1L, null);
        }).isInstanceOf(ResponseStatusException.class);
    }

    @Test
    void addNewCategory() {
    }

    @Test
    void deleteCategoryByName() {
    }

    @Test
    void deleteCategoryById() {
    }

    @Test
    void updateCategory() {
    }
}