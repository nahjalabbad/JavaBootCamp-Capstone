package org.example.capstone1.Service;


import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
    ArrayList<Product> products = new ArrayList<>();
    private final CategoryService categoryService;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean addProducts(Product product) {
        for (int i = 0; i < categoryService.categories.size(); i++) {
            if (categoryService.categories.get(i) != null && categoryService.categories.get(i).getId().equals(product.getCategoryID())) {
                products.add(product);
                return true;
            }
        }
        return false;
    }


    public boolean updateProducts(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProducts(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }


}
