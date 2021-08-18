package com.shreeharibi.expensetracker.service;

import com.shreeharibi.expensetracker.category.ExpenseRepository;
import com.shreeharibi.expensetracker.exceptions.ExpenseExistsException;
import com.shreeharibi.expensetracker.exceptions.ExpenseNotFoundException;
import com.shreeharibi.expensetracker.model.Category;
import com.shreeharibi.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Expense getExpenseById(Long id) throws ExpenseNotFoundException {
        Optional<Expense> _expense = expenseRepository.findById(id);

        if (!_expense.isPresent()) {
            throw new ExpenseNotFoundException("Expense does not exist...");
        }

        return _expense.get();
    }

    public Expense getExpenseByName(String name) throws ExpenseNotFoundException {
        Optional<Expense> _expense = expenseRepository.findExpenseByName(name);

        if (!_expense.isPresent()) {
            throw new ExpenseNotFoundException("Expense does not exist...");
        }

        return _expense.get();
    }

    public Expense addExpense(Expense expense) throws ExpenseExistsException {
        Optional<Expense> _expense = expenseRepository.findExpenseByName(expense.getName());
        if (_expense.isPresent()) {
            throw new ExpenseExistsException("Expense already exists");
        }

        expenseRepository.save(expense);
        return expense;
    }

    public void deleteExpenseById(Long expenseId) {
        Optional<Expense> _expense = expenseRepository.findById(expenseId);

        if (_expense.isPresent()) {
            System.out.println("Deleting expense id " + expenseId);
            expenseRepository.deleteById(expenseId);
        }
        return;
    }

    public ResponseEntity<Expense> updateoldExpense(Long oldExpenseId, Expense expense) throws ExpenseNotFoundException {
        Optional<Expense> _expense = expenseRepository.findById(oldExpenseId);

        if (!_expense.isPresent()) {
            throw new ExpenseNotFoundException("Expense does not exist...");
        }

        _expense.get().setName(expense.getName());
        _expense.get().setAmount(expense.getAmount());
        _expense.get().setCategoryId(expense.getCategoryId());
        _expense.get().setComments(expense.getComments());
        _expense.get().setCreationDate(expense.getCreationDate());
        Expense updatedExpense = expenseRepository.save(_expense.get());
        return ResponseEntity.ok(updatedExpense);
    }
}
