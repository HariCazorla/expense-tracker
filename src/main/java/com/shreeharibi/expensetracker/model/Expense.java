package com.shreeharibi.expensetracker.model;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense {
    @NotEmpty(message = "Expense name cannot be empty")
    private String name;

    @Id
    @SequenceGenerator(
            name = "expense_sequence",
            sequenceName = "expense_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "expense_sequence"
    )
    private Long expenseId;
    private Long categoryId;

    @Min(1)
    private Double amount;

    @Past
    private LocalDate creationDate;
    private String comments;

    public Expense() {
    }

    public Expense(String name, Long expenseId, Long categoryId, Double amount, LocalDate creationDate, String comments) {
        this.name = name;
        this.expenseId = expenseId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.creationDate = creationDate;
        this.comments = comments;
    }

    public Expense(String name, Long categoryId, Double amount, LocalDate creationDate, String comments) {
        this.name = name;
        this.categoryId = categoryId;
        this.amount = amount;
        this.creationDate = creationDate;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
