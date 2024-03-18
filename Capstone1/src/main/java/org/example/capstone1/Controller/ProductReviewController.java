package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.API.ApiResponse;
import org.example.capstone1.Model.ProductReview;
import org.example.capstone1.Service.ProductReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/api/v1/review")
@RestController
@RequiredArgsConstructor
public class ProductReviewController {
    private final ProductReviewService productReviewService;

    @GetMapping("/get")
    public ResponseEntity getReviews() {
        return ResponseEntity.ok(productReviewService.getReviews());
    }

    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody @Valid ProductReview review, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isReviewd=productReviewService.addReview(review);
        if (isReviewd){
            return ResponseEntity.ok().body(new ApiResponse("Your review has been submitted"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateReviews(@PathVariable String id, @RequestBody @Valid ProductReview review, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }

        boolean isUpdated= productReviewService.updateReviews(id,review);
        if (isUpdated){
            return ResponseEntity.ok().body(new ApiResponse("Your review has been updated"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReviews(@PathVariable String id) {
        boolean isDeleted = productReviewService.deleteReviews(id);
        if (isDeleted) {
            return ResponseEntity.ok().body(new ApiResponse("Your Review has been deleted"));
        }
            return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
    }

    @GetMapping("/getproductreviews/{id}")
    public ResponseEntity getProductReviews(@PathVariable String id){
        ArrayList<ProductReview>getReviews=productReviewService.getProductReviews(id);
        if (getReviews!=null){
            return ResponseEntity.ok().body(getReviews);
        }
        return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
    }

    @GetMapping("/getavgrating/{pName}")
    public ResponseEntity getAvgRating(@PathVariable String pName) {
        Double avgRating = productReviewService.getAvgRating(pName);
        if (avgRating != null) {
            return ResponseEntity.ok().body(new ApiResponse("Average Rating for " + pName + " is: " + avgRating));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Product not found or no reviews"));
    }

}
