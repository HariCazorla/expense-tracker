package com.shreeharibi.expensetracker.controller;

import com.shreeharibi.expensetracker.model.Expense;
import com.shreeharibi.expensetracker.service.ExpenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(path = "about")
    @ApiOperation("General information on expense-tracker.")
    public String selfDescription() {
        return "Welcome to expense-tracking version 1.0.0. New features will be added soon...";
    }

    @GetMapping()
    @ApiOperation("Returns list of all expenses in the system.")
    public List<Expense> getExpenseByIdorName(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {
        List<Expense> result = new ArrayList<Expense>();
        if ((id == null) && (name == null)) {
            result = expenseService.getExpenses();
        }
        else if (name == null) {
            result.add(expenseService.getExpenseById(id));
        }
        else {
            result.add(expenseService.getExpenseByName(name));
        }
        return result;
    }

    @PostMapping
    @ApiOperation("Add a new expense to the system.")
    public Expense addExpense(@RequestBody Expense expense) {
        try {
            return expenseService.addExpense(expense);
        } catch (DateTimeParseException exception) {
            throw new IllegalStateException("Invalid date format, use YYYY-MM-DD format");
        }
    }
}
