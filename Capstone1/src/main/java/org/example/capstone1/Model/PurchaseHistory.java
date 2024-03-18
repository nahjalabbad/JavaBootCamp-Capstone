package org.example.capstone1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PurchaseHistory {

    private String productID;
    private String userID;
    private String productName;
    private double price;
    private int quantity;
    private LocalDate purchaseDate;
}
