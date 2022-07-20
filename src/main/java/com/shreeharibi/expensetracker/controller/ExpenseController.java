package com.shreeharibi.expensetracker.controller;

import com.shreeharibi.expensetracker.exceptions.ExpenseExistsException;
import com.shreeharibi.expensetracker.exceptions.ExpenseNotFoundException;
import com.shreeharibi.expensetracker.model.Expense;
import com.shreeharibi.expensetracker.service.ExpenseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/expenses")
@RequiredArgsConstructor
@Slf4j
public class ExpenseController {

    private final ExpenseService expenseService;

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
        List<Expense> result = new ArrayList<>();
        try {
            if ((id == null) && (name == null)) {
                result = expenseService.getExpenses();
            }
            else if (name == null) {
                result.add(expenseService.getExpenseById(id));
            }
            else {
                result.add(expenseService.getExpenseByName(name));
            }
        } catch (ExpenseNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return result;
    }

    @PostMapping
    @ApiOperation("Add a new expense to the system.")
    public Expense addExpense(@RequestBody Expense expense) {
        try {
            return expenseService.addExpense(expense);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Invalid date format, use YYYY-MM-DD format", e);
        } catch (ExpenseExistsException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping(path = "{expenseId}")
    public void deleteExpenseById(
            @PathVariable("expenseId") Long expenseId
    ) {
        try {
            expenseService.deleteExpenseById(expenseId);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping(path = "{oldExpenseId}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable("oldExpenseId") Long oldExpenseId,
            @RequestBody Expense expense
    ) {
        try {
            return expenseService.updateoldExpense(oldExpenseId, expense);
        } catch (DateTimeParseException | ExpenseNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
