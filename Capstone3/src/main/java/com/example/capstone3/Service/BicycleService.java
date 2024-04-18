package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BicycleService {
    private  final BicycleRepository bicycleRepository;
    private final CompanyRepository companyRepository;
    private final StationRepository stationRepository;
    private final RentRepository rentRepository;
    private final RentalHistoryRepository rentalHistoryRepository;



    public List<Bicycle> getAllBicycles(){
        return bicycleRepository.findAll();
    }

    public void addBicycle(Integer companyId, Bicycle bicycle) {
        Company company = companyRepository.findCompanyByCompanyId(companyId);
        if (company == null) {
            throw new ApiException("Company id not found!");
        }
        if (!company.getTransportType().equalsIgnoreCase("Bicycle")){
            throw new ApiException("Company transport type is not set to bicycle");
        }
        bicycle.setRentStatus("Not Rented");
        bicycle.setCompany(company);

        bicycleRepository.save(bicycle);
    }

    public void updateBicycle(Integer bicycleId,Bicycle newBicycle){
        Bicycle bicycle1=bicycleRepository.findBicycleByBicycleId(bicycleId);
        if(bicycle1==null){
            throw new ApiException("Bicycle id not found!");
        }
        bicycle1.setFeatures(newBicycle.getFeatures());
        bicycle1.setLocation(newBicycle.getLocation());
        bicycle1.setModel(newBicycle.getModel());
        bicycle1.setPinNumber(newBicycle.getPinNumber());
        bicycle1.setNumOfWheels(newBicycle.getNumOfWheels());
        bicycleRepository.save(bicycle1);
    }

    public void deleteBicycle(Integer bicycleId){
        Bicycle bicycle=bicycleRepository.findBicycleByBicycleId(bicycleId);
        if(bicycle==null){
            throw new ApiException("Bicycle id not found!");
        }
        bicycleRepository.delete(bicycle);
    }

    //Extra

    public List<Bicycle>byTypeWeels(Integer numberOfWeels){
        List<Bicycle> bicycles=bicycleRepository.findBicycleByNumOfWheels(numberOfWeels);
        if ((bicycles.isEmpty())){
            throw new ApiException("not Avalabile");
        }
        return bicycles;
    }

    public List<Bicycle>byModel(Integer model){
        List<Bicycle> bicycles=bicycleRepository.findBicycleByModel(model);
        if ((bicycles.isEmpty())){
            throw new ApiException("not Avalabile");
        }
        return bicycles;
    }

    public List<Bicycle>viewAValibalAllBick(){
        List<Bicycle> bicycles=bicycleRepository.findBicycleByRentStatus("Not Rented");
        if(bicycles.isEmpty()){
            throw new ApiException("No Bicycle are available");
        }
        return bicycles;
    }

    public String getSpecificDetails(String bicycleName){
        Bicycle bicycle=bicycleRepository.findBicycleByBicycleName(bicycleName);
        if(bicycle==null){
            throw new ApiException("Bicycle name not found!");
        }
        return  " Bicycle Details: " + bicycle.getBicycleName() + " " + bicycle.getModel() +" "+ bicycle.getReturnStatus() + " " + bicycle.getFeatures() + "  " + bicycle.getLocation() + "  " + bicycle.getNumOfWheels() ;
    }

    public Double getAvgRating(String bicycleName) {
        List<RentalHistory> rentalHistories = rentalHistoryRepository.findRentalHistoriesByTransportName(bicycleName);
        double sum = 0;
        int count = 0;

        if (rentalHistories.isEmpty()) {
            throw new ApiException("No rental history found for this bicycle name");
        }

        for (RentalHistory rh : rentalHistories) {
            sum += rh.getRating();
            count++;
        }

        return sum / count;
    }


}
