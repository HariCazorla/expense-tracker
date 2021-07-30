package com.shreeharibi.expensetracker.config;

import com.shreeharibi.expensetracker.category.Category;
import com.shreeharibi.expensetracker.category.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(
            CategoryRepository categoryRepository) {
        return args -> {
            Category groceries = new Category(
                    1L,
                    "groceries",
                    "You can add groceries here"
            );
            Category travel = new Category(
                    2L,
                    "travel",
                    "You can add travel here"
            );
            Category concerts = new Category(
                    3L,
                    "concerts",
                    "You can add concerts here"
            );
            Category cinema = new Category(
                    4L,
                    "cinema",
                    "You can add cinema here"
            );
            Category fitness = new Category(
                    5L,
                    "fitness",
                    "You can add fitness here"
            );

            categoryRepository.saveAll(
                    List.of(
                            groceries,
                            travel,
                            concerts,
                            cinema,
                            fitness
                    )
            );
        };
    }
}
