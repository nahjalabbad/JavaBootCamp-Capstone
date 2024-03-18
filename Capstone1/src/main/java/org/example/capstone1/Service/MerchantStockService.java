package org.example.capstone1.Service;


import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor

public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final MerchantService merchantService;
    private final ProductService productService;
    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public boolean addMerchantStocks(MerchantStock merchantStock) {
        for (int i = 0; i < Math.min(merchantService.merchants.size(), productService.products.size()); i++) {
            if (merchantService.merchants.get(i) != null &&
                    merchantService.merchants.get(i).getId().equals(merchantStock.getMerchantID()) &&
                    productService.products.get(i) != null &&
                    productService.products.get(i).getId().equals(merchantStock.getProductID())) {

                merchantStocks.add(merchantStock);
                return true;
            }
        }
        return false;
    }



    public boolean updateMerchantStocks(String id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStocks(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addStock(String productID, String merchantID, int amount) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductID().equals(productID) && merchantStock.getMerchantID().equals(merchantID)) {
                if (merchantStock.getStock() > amount) {
                    merchantStock.setStock(merchantStock.getStock() + amount);
                    return true;
                }

            }
        }
        return false;
    }

}


