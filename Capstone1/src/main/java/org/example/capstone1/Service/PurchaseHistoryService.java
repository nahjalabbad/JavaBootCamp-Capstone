package org.example.capstone1.Service;

import org.example.capstone1.Model.PurchaseHistory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseHistoryService {
    private final List<PurchaseHistory> purchases = new ArrayList<>();

    public void addPurchaseRecord(PurchaseHistory purchaseRecord) {
        purchases.add(purchaseRecord);
    }

    public ArrayList<PurchaseHistory> getPurchaseHistoryByUserId(String userId) {
        ArrayList<PurchaseHistory> userPurchases = new ArrayList<>();
        for (PurchaseHistory purchase : purchases) {
            if (purchase.getUserID().equalsIgnoreCase(userId)) {
                userPurchases.add(purchase);
            }
        }
        if(userPurchases.isEmpty()){
            return null;
        }
        return userPurchases;
    }

    public ArrayList<PurchaseHistory> getPurchaseHistoryByProductId(String productId) {
        ArrayList<PurchaseHistory> productPurchases = new ArrayList<>();
        for (PurchaseHistory purchase : purchases) {
            if (purchase.getProductID().equalsIgnoreCase(productId)) {
                productPurchases.add(purchase);
            }
        }
        if (productPurchases.isEmpty()){
            return null;
        }
        return productPurchases;
    }
}

