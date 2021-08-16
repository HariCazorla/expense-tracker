package com.shreeharibi.expensetracker.exceptions;

public class CategoryDuplicateException extends Exception{
    public CategoryDuplicateException(String message) {
        super(message);
    }

    public CategoryDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
