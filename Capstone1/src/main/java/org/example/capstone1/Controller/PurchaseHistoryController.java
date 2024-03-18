package org.example.capstone1.Controller;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.API.ApiResponse;
import org.example.capstone1.Model.PurchaseHistory;
import org.example.capstone1.Service.PurchaseHistoryService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchasehistory")
@RequiredArgsConstructor
public class PurchaseHistoryController {
    private final PurchaseHistoryService purchaseHistoryService;

    @GetMapping("/byuser/{userId}")
    public ResponseEntity getPurchaseHistoryByUserId(@PathVariable String userId) {
        List<PurchaseHistory> userPurchaseHistory = purchaseHistoryService.getPurchaseHistoryByUserId(userId);
        if (userPurchaseHistory==null) {
            return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
        }
        return ResponseEntity.ok(userPurchaseHistory);
    }

    @GetMapping("/byproduct/{productId}")
    public ResponseEntity getPurchaseHistoryByProductId(@PathVariable String productId) {
        List<PurchaseHistory> productPurchaseHistory = purchaseHistoryService.getPurchaseHistoryByProductId(productId);
        if (productPurchaseHistory==null) {
            return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
        }
        return ResponseEntity.ok(productPurchaseHistory);
    }

}

