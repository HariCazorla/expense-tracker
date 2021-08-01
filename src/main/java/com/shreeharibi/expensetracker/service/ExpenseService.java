package com.shreeharibi.expensetracker.service;

import com.shreeharibi.expensetracker.category.ExpenseRepository;
import com.shreeharibi.expensetracker.model.Category;
import com.shreeharibi.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        Optional<Expense> _expense = expenseRepository.findById(id);

        if (!_expense.isPresent()) {
            throw new IllegalStateException("Expense does not exist...");
        }

        return _expense.get();
    }

    public Expense getExpenseByName(String name) {
        Optional<Expense> _expense = expenseRepository.findExpenseByName(name);

        if (!_expense.isPresent()) {
            throw new IllegalStateException("Expense does not exist...");
        }

        return _expense.get();
    }

    public Expense addExpense(Expense expense) {
        Optional<Expense> _expense = expenseRepository.findById(expense.getExpenseId());
        if (_expense.isPresent()) {
            throw new IllegalStateException("Expense already exists");
        }

        expenseRepository.save(expense);
        return expense;
    }
}
