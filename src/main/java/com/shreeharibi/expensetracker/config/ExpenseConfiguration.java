package com.shreeharibi.expensetracker.config;

import com.shreeharibi.expensetracker.category.ExpenseRepository;
import com.shreeharibi.expensetracker.model.Expense;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class ExpenseConfiguration {
    @Bean
    CommandLineRunner commandLineRunnerExpenses(
            ExpenseRepository expenseRepository
    ){
        return args -> {
            Expense rent = new Expense(
                    "Rent", 1L, 1L, 200.0, LocalDate.now().minusDays(1), "Room rent"
            );

            expenseRepository.saveAll(
                    List.of(
                            rent
                    )
            );
        };
    }
}
