package com.shreeharibi.expensetracker.controller;

import com.shreeharibi.expensetracker.model.Expense;
import com.shreeharibi.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String selfDescription() {
        return "Welcome to expense-tracking version 1.0.0. New features will be added soon...";
    }

    @GetMapping()
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
}
