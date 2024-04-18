package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Model.RentalHistory;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.RentalHistoryRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RentalHistoryRepository rentalHistoryRepository;

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer userId , User user){
        User u = userRepository.findUserByUserId(userId);
        if(u==null){
            throw new ApiException("User not found");
        }
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());
        u.setRating(user.getRating());
        u.setComment(user.getComment());
        u.setPassword(user.getPassword());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setRide(user.getRide());
        userRepository.save(u);
    }

    public void deleteUser(Integer userId){
        User u = userRepository.findUserByUserId(userId);
        if(u==null){
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }


    //EXTRA

    public void addCommentAndRating(Integer userId , Integer rentalId, Integer rating,String comment){
        User u = userRepository.findUserByUserId(userId);
        RentalHistory r = rentalHistoryRepository.findRentalHistoryByRentalId(rentalId);


        if(u==null || r==null){
            throw new ApiException("User not found");
        }
        if (!r.getUserId().equals(userId)){
            throw new ApiException("the rental history is not attached to this user");
        }
        u.setRating(rating);
        u.setComment(comment);
        userRepository.save(u);
        r.setRating(rating);
        r.setComment(comment);
        rentalHistoryRepository.save(r);
    }


    public String getTotalRides(Integer userId ){
        User u = userRepository.findUserByUserId(userId);
        if(u==null ){
            throw new ApiException("User not found");
        }

        return "Total Rides:" + u.getRide();
    }

}
