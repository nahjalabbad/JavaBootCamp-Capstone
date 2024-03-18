package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Model.ProductReview;
import org.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

    private final ProductService productService;
    private final UserService userService;


    ArrayList<ProductReview> reviews = new ArrayList<>();

    public ArrayList<ProductReview> getReviews() {
        return reviews;
    }


    public boolean addReview(ProductReview review) {
        for (int i = 0; i < Math.min(productService.products.size(),userService.users.size()); i++) {
            if (productService.products.get(i).getId().equalsIgnoreCase(review.getProductID())&&userService.users.get(i).getId().equalsIgnoreCase(review.getUserID())) {
                reviews.add(review);
                return true;
            }
        }
        return false;
    }

    public boolean updateReviews(String id, ProductReview review) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getId().equals(id)) {
                reviews.set(i, review);
                return true;
            }
        }
        return false;
    }

    public boolean deleteReviews(String id) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getId().equals(id)) {
                reviews.remove(i);
                return true;
            }
        }
        return false;
    }



    public ArrayList<ProductReview> getProductReviews(String id) {
        ArrayList<ProductReview> getReviews = new ArrayList<>();
        for (ProductReview p : reviews) {
            if (p.getId().equalsIgnoreCase(id)) {
                getReviews.add(p);
            }
        }
        if (getReviews.isEmpty()){
            return null;
        }
        return getReviews;
    }

    public Double getAvgRating(String pName) {
        double sum = 0;
        int count = 0;
        boolean found = false;
        for (ProductReview p : reviews) {
            for (Product product : productService.products) {
                if (product.getName().equalsIgnoreCase(pName)) {
                    sum += p.getRating();
                    count++;
                    found = true;
                }
            }
        }
        if (!found || count == 0) {
            return null;
        }
        return sum / count;
    }
}
