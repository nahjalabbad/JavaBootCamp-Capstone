package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.API.ApiResponse;
import org.example.capstone1.Model.Merchant;
import org.example.capstone1.Service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchants() {
        return ResponseEntity.ok(merchantService.getMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchants(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        merchantService.addMerchants(merchant);
        return ResponseEntity.ok().body(new ApiResponse("Merchant has been added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchants(@PathVariable String id, @RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdated = merchantService.updateMerchants(id, merchant);
        if (isUpdated) {
            return ResponseEntity.ok().body(new ApiResponse("Merchant has been updated"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchants(@PathVariable String id) {
        boolean isDeleted = merchantService.deleteMerchants(id);
        if (isDeleted) {
            return ResponseEntity.ok().body(new ApiResponse("Merchant has been deleted"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("ID not found"));
        }
    }
}
