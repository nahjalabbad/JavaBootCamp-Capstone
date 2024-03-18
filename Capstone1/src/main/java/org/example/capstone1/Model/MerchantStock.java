package org.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "ID Cannot be empty")
    private String id;

    @NotEmpty(message = "Product ID Cannot be empty")
    private String productID;

    @NotEmpty(message = "Merchant ID Cannot be empty")
    private String merchantID;

    @NotNull(message = "Stock Cannot be empty")
    @Min(10)
    private int stock;


}
