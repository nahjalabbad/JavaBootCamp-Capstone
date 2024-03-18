package org.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductReview {
    @NotEmpty(message = "ID Cannot be empty")
    private String id;

    @NotEmpty(message = "Product ID Cannot be empty")
    private String productID;

    @NotEmpty(message = "User ID Cannot be empty")
    private String userID;


    @NotNull(message = "Please provide your rating")
    @Min(0)
    @Max(5)
    private double rating;

    @NotEmpty(message = "Please write a comment")
    @Size(min = 10,max = 500)
    private String reviewComment;
}
