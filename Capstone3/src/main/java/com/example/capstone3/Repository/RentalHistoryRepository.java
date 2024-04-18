package com.example.capstone3.Repository;

import com.example.capstone3.Model.Rent;
import com.example.capstone3.Model.RentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalHistoryRepository extends JpaRepository<RentalHistory,Integer> {
    RentalHistory findRentalHistoryByRentalId(Integer rentalId);

    List<RentalHistory> findRentalHistoriesByUserId(Integer userId);

    @Query("select user from Rent user where user.user.userId=?1 and user.transportType=?2")
    List<RentalHistory> findRentalHistoriesByUserIdaAndTransportType(Integer userId, String transportType);

    List<RentalHistory> findRentalHistoriesByUserIdAndStatus(Integer userId,String status);

    List<RentalHistory> findRentalHistoriesByTransportName(String name);


    RentalHistory findRentalHistoriesByRent(Rent rent );


    @Query("SELECT review.comment FROM RentalHistory review WHERE review.transportName = ?1")
    List<String> getRatingsByTransportName(String name);

}
