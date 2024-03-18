package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.API.ApiResponse;
import org.example.capstone1.Model.Category;
import org.example.capstone1.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        categoryService.addCategory(category);
            return ResponseEntity.ok().body(new ApiResponse("Category has been added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdated = categoryService.updateCategory(id, category);
        if (isUpdated) {
            return ResponseEntity.ok().body(new ApiResponse("Category has been updated"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return ResponseEntity.ok().body(new ApiResponse("Category has been deleted"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
        }
    }
}
