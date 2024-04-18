package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.RentalHistoryDTO;
import com.example.capstone3.Model.Rent;
import com.example.capstone3.Model.RentalHistory;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.RentRepository;
import com.example.capstone3.Repository.RentalHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalHistoryService {
    private final RentalHistoryRepository rentalHistoryRepository;
    private final RentRepository rentRepository;

    public List<RentalHistory> getRentalHistory() {
        return rentalHistoryRepository.findAll();
    }

    public RentalHistory addRentalHistory(Integer rentId, RentalHistoryDTO rentalHistoryDTO) {
        Rent rent = rentRepository.findRentByRentId(rentId);

        if (rent == null) {
            throw new ApiException("Rent id not found");
        }

        RentalHistory rentalHistory = new RentalHistory(null, rentalHistoryDTO.getUserId(), rentalHistoryDTO.getTransportName(),
                rentalHistoryDTO.getPickUpStation(), rentalHistoryDTO.getDropOffStation(), rentalHistoryDTO.getStartTime(),
                rentalHistoryDTO.getEndTime(), rentalHistoryDTO.getDuration(), rentalHistoryDTO.getStatus(),
                rentalHistoryDTO.getFuelLevel(), rentalHistoryDTO.getRating(), rentalHistoryDTO.getComment(), rent);

        rentalHistory.setRent(rent);
        rentalHistoryRepository.save(rentalHistory);

        return rentalHistory;
    }

    public void updateRentalHistory(Integer rentalId, RentalHistoryDTO rentalHistoryDTo) {
        RentalHistory rentalHistory1 = rentalHistoryRepository.findRentalHistoryByRentalId(rentalId);
        if (rentalHistory1 == null) {
            throw new ApiException("rentalId not found");
        }
        rentalHistory1.setPickUpStation(rentalHistoryDTo.getPickUpStation());
        rentalHistory1.setDropOffStation(rentalHistoryDTo.getDropOffStation());
        rentalHistory1.setStartTime(rentalHistoryDTo.getStartTime());
        rentalHistory1.setEndTime(rentalHistoryDTo.getEndTime());
        rentalHistory1.setDuration(rentalHistoryDTo.getDuration());
        rentalHistory1.setStatus(rentalHistoryDTo.getStatus());
        rentalHistory1.setFuelLevel(rentalHistoryDTo.getFuelLevel());

        rentalHistoryRepository.save(rentalHistory1);

    }

    public void deleteRentalHistory(Integer rentalId) {
        RentalHistory rentalHistory = rentalHistoryRepository.findRentalHistoryByRentalId(rentalId);
        if (rentalHistory == null) {
            throw new ApiException("rentalId not found");
        }
        rentalHistoryRepository.delete(rentalHistory);
    }



    //              EXTRA

    public List<RentalHistory> getAllHistory(Integer userId){
        List<RentalHistory> userRent=rentalHistoryRepository.findRentalHistoriesByUserId(userId);
        if (userRent.isEmpty()){
            throw new ApiException("This user does not have any rental history");
        }
        return userRent;
    }

    public List<RentalHistory> getHistoryByType(Integer userId, String transportType){
        List<RentalHistory> userRent=rentalHistoryRepository.findRentalHistoriesByUserIdaAndTransportType(userId,transportType);
        Rent rent=rentRepository.findRentByUserUserId(userId);
        if (userRent.isEmpty()){
            throw new ApiException("This user does not have any rental history");
        }
        if (!rent.getTransportType().equalsIgnoreCase(transportType)){
            throw new ApiException("this transport type is not available in our website");
        }

        return userRent;
    }

    public List<RentalHistory> getByStatus(Integer userId,String status){
        List<RentalHistory> userRent=rentalHistoryRepository.findRentalHistoriesByUserIdAndStatus(userId,status);
        if (userRent.isEmpty()){
            throw new ApiException("This user does not have any rental history");
        }
        return userRent;
    }

    public List<String> getReview( String transName){
        List<String> comment = rentalHistoryRepository.getRatingsByTransportName(transName);


        if(comment.isEmpty()){
            throw new ApiException("Transport name is not found");
        }

        return comment;
    }



}
