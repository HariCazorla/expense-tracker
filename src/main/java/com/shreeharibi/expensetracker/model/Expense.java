package com.shreeharibi.expensetracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "expense")
@ApiModel(description = "class representing the expense")
public class Expense {
    @ApiModelProperty(notes = "Name of the item", example = "Shoes", required = true, allowEmptyValue = false)
    @NotEmpty(message = "Expense name cannot be empty")
    private String name;

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @ApiModelProperty(notes = "ID is auto generated", example = "NA", required = false, allowEmptyValue = true)
    private Long expenseId;
    @ApiModelProperty(notes = "Category ID, use category end points to find out suitable one", example = "1", required = true, allowEmptyValue = false)
    private Long categoryId;

    @Min(1)
    @ApiModelProperty(notes = "Amount of the item", example = "70", required = true, allowEmptyValue = false)
    private Double amount;

    @PastOrPresent
    @ApiModelProperty(notes = "Date format YYYY-MM-DD", example = "2021-07-31", required = false, allowEmptyValue = true)
    private LocalDate creationDate;
    @ApiModelProperty(notes = "Additional information", example = "Nike running shoes", required = false, allowEmptyValue = true)
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
