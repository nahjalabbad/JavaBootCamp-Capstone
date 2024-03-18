package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.API.ApiResponse;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProducts(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean addProduct=productService.addProducts(product);
        if (addProduct) {
            return ResponseEntity.ok().body(new ApiResponse("Product has been added"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Category ID does not match"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProducts(@PathVariable String id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdated = productService.updateProducts(id,product);
        if (isUpdated) {
            return ResponseEntity.ok().body(new ApiResponse("Product has been updated"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProducts(@PathVariable String id) {
        boolean isDeleted = productService.deleteProducts(id);
        if (isDeleted) {
            return ResponseEntity.ok().body(new ApiResponse("Product has been deleted"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
        }
    }

}
