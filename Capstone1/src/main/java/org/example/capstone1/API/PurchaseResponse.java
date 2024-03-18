package org.example.capstone1.API;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseResponse {
    private int userPoints;
    private int deductedUserPoints;
    private int userPointsAfterDeduction;
    private double discountedPrice;
    private double productPriceBeforeDiscount;
    private double productPriceAfterDiscount;
    private double userNewBalance;
}
