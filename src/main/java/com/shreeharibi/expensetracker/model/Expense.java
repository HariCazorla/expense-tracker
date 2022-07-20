package com.shreeharibi.expensetracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "expense")
@ApiModel(description = "class representing the expense")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @ApiModelProperty(notes = "Name of the item", example = "Shoes", required = true, allowEmptyValue = false)
    @NotEmpty(message = "Expense name cannot be empty")
    private String name;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
//    @ApiModelProperty(notes = "ID is auto generated", example = "NA", required = false, allowEmptyValue = true)
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
    @Size(max = 500)
    private String comments;
}
