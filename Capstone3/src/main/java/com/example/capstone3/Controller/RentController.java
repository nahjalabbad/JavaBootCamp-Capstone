package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.Model.Rent;
import com.example.capstone3.Service.RentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;

    @GetMapping("/get")
    public ResponseEntity getRent(){
        return ResponseEntity.status(200).body(rentService.getRent());
    }

    @PostMapping("/add/{userId}/{companyName}/{transName}/{duration}")
    public ResponseEntity addRent(@PathVariable Integer userId,@PathVariable String companyName, @PathVariable String transName, @PathVariable String duration, @RequestBody @Valid Rent rent ){
        rentService.addRent(userId,companyName,transName,duration,rent);
        return ResponseEntity.status(200).body(new ApiResponse("Rent Added and Assigned to user "));
    }

    @PutMapping("/update/{rentId}")
    public ResponseEntity updateRent( @PathVariable Integer rentId, @RequestBody @Valid Rent rent ){
        rentService.updateRent(rentId,rent);
        return ResponseEntity.status(200).body(new ApiResponse("Rent Updated"));
    }

    @DeleteMapping("/delete/{rentId}")
    public ResponseEntity deleteRent(@PathVariable Integer rentId ){
        rentService.deleteRent(rentId);
        return ResponseEntity.status(200).body(new ApiResponse("Rent Deleted"));
    }

    //              EXTRA

    @GetMapping("/available")
    public ResponseEntity getAllAvailable(){
        return ResponseEntity.status(200).body(rentService.byAvalablity());
    }

    // extra
    @PutMapping("/return/{userId}/{companyName}/{rentId}")
    public ResponseEntity returnRent(@PathVariable Integer userId,@PathVariable String companyName, @PathVariable Integer rentId){
        rentService.returnRent(userId, companyName, rentId);
        return ResponseEntity.status(200).body(new ApiResponse("thank you, your return has been submitted"));
    }
}
