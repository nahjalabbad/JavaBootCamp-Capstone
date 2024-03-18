package org.example.capstone1.Service;




import lombok.RequiredArgsConstructor;
import org.example.capstone1.API.PurchaseResponse;
import org.example.capstone1.Model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final MerchantService merchantService;
    private final PurchaseHistoryService purchaseHistoryService;
    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        user.setPoints(25);
        users.add(user);
    }

    public boolean updateUsers(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUsers(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public PurchaseResponse buyProduct(String userID, String productID, String merchantID, int amount) {
        for (User user : users) {
            if (user.getId().equalsIgnoreCase(userID)) {
                for (int j = 0; j < merchantService.merchants.size(); j++) {
                    if (merchantService.merchants.get(j).getId().equalsIgnoreCase(merchantID)) {
                        for (int k = 0; k < productService.products.size(); k++) {
                            if (productService.products.get(k).getId().equalsIgnoreCase(productID)){
                                if (merchantStockService.merchantStocks.get(j).getStock() >= amount && user.getBalance() >= productService.products.get(j).getPrice()) {
                                    double discount = getDiscount(user);
                                    double totalPrice = productService.products.get(k).getPrice() * amount;
                                    user.setPoints(user.getPoints()+25);

                                    //Points
                                    int pointsBefore = user.getPoints();
                                    int pointsAfter=pointsBefore-25;
                                    int pointsDeducted=pointsBefore-pointsAfter;

                                    //Price
                                    double productPriceBeforeDiscount = productService.products.get(k).getPrice() * amount;
                                    double discountedApplied = productPriceBeforeDiscount * (1 - discount);
                                    double discountedPrice = productPriceBeforeDiscount - discountedApplied;
                                    double priceAfterDiscount = totalPrice * (1 - discount);

                                    //After Purchasing
                                    double userNewBalance=(user.getBalance() - priceAfterDiscount);
                                    user.setBalance(userNewBalance);
                                    merchantStockService.merchantStocks.get(k).setStock(merchantStockService.merchantStocks.get(k).getStock() - amount);

                                    // Create purchase history record
                                    PurchaseHistory purchaseHistoryRecord = new PurchaseHistory(productID, userID, productService.products.get(k).getName(),
                                            productService.products.get(k).getPrice(), amount, LocalDate.now());
                                    purchaseHistoryService.addPurchaseRecord(purchaseHistoryRecord);

                                    return new PurchaseResponse(pointsBefore,pointsDeducted,pointsAfter,discountedPrice,productPriceBeforeDiscount,priceAfterDiscount,userNewBalance);
                                } else {
                                    return null;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private double getDiscount(User user) {
        int points = user.getPoints();
        if (points >= 1000) {
            return 0.35;
        } else if (points >= 500) {
            return 0.25;
        } else if (points >= 100) {
            return 0.10;
        } else if (points >= 50) {
            return 0.05;
        } else {
            return 0;
        }
    }




}





