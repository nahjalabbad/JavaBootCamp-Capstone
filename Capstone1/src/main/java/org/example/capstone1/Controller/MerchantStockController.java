package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.API.ApiResponse;
import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Service.MerchantStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchantstock")
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStocks() {
        return ResponseEntity.ok(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStocks(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean addStock= merchantStockService.addMerchantStocks(merchantStock);
        if (addStock) {
            return ResponseEntity.ok().body(new ApiResponse("Product Stock has been added"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Merchant ID or Product ID is incorrect "));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStocks(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdated = merchantStockService.updateMerchantStocks(id,merchantStock);
        if (isUpdated) {
            return ResponseEntity.ok().body(new ApiResponse("Product Stock has been updated"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStocks(@PathVariable String id) {
        boolean isDeleted = merchantStockService.deleteMerchantStocks(id);
        if (isDeleted) {
            return ResponseEntity.ok().body(new ApiResponse("Product stock has been deleted"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
        }
    }


    @PutMapping("/addstock/{productID}/{merchantID}/{amount}")
    public ResponseEntity addStock(@PathVariable String productID, @PathVariable String merchantID, @PathVariable int amount) {
        boolean addStock = merchantStockService.addStock(productID,merchantID,amount);
        if (addStock) {
            return ResponseEntity.ok().body(new ApiResponse("Product has been stocked with the amount "+amount));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
        }
    }

}
