package org.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "ID Cannot be empty")
    private String id;

    @Size(min=3)
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Please write a price")
    @Positive
    private double price;

    @NotEmpty(message = "Category ID cannot be empty")
    private String categoryID;
}
