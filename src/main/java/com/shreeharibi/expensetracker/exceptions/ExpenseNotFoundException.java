package com.shreeharibi.expensetracker.exceptions;

public class ExpenseNotFoundException extends Exception{
    public ExpenseNotFoundException(String message) {
        super(message);
    }

    public ExpenseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
