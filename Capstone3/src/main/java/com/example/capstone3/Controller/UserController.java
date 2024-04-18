package com.example.capstone3.Controller;
import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.Model.User;
import com.example.capstone3.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getUser(){
        return ResponseEntity.status(200).body(userService.getUser());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity updateUser(@PathVariable Integer userId , @RequestBody @Valid User user ){
        userService.updateUser(userId , user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated"));
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity deleteUser(@PathVariable Integer userId ){
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }

    //              EXTRA
    @PutMapping("/addreview/{userId}/{rentalId}/{rating}")
    public ResponseEntity addCommentAndRating(@PathVariable Integer userId , @PathVariable Integer rentalId,@PathVariable Integer rating, @RequestBody @Valid String comment ){
        userService.addCommentAndRating(userId , rentalId,rating, comment);
        return ResponseEntity.status(200).body(new ApiResponse("user comment and rating added"));
    }



    @GetMapping("/getrides/{userId}")
    public ResponseEntity getTotalRides(@PathVariable Integer userId ){
        return ResponseEntity.status(200).body(userService.getTotalRides(userId));
    }
}
