package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.API.ApiResponse;
import org.example.capstone1.API.PurchaseResponse;
import org.example.capstone1.Model.User;
import org.example.capstone1.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user/")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUsers(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.addUsers(user);
        return ResponseEntity.ok().body(new ApiResponse("User has been added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUsers(@PathVariable String id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdated = userService.updateUsers(id,user);
        if (isUpdated) {
            return ResponseEntity.ok().body(new ApiResponse("User has been updated"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUsers(@PathVariable String id) {
        boolean isDeleted = userService.deleteUsers(id);
        if (isDeleted) {
            return ResponseEntity.ok().body(new ApiResponse("User has been deleted"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
        }
    }

    @PutMapping("/buy/{userID}/{productID}/{merchantID}/{amount}")
    public ResponseEntity buyProduct(@PathVariable String userID, @PathVariable String productID, @PathVariable String merchantID, @PathVariable int amount) {
        PurchaseResponse purchaseResponse = userService.buyProduct(userID, productID, merchantID, amount);
        if (purchaseResponse != null) {
            return ResponseEntity.ok().body(purchaseResponse);
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("IDs not found or the product is not available"));
        }
    }

}
