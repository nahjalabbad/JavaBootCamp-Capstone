package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterRepository scooterRepository;
    private final CompanyRepository companyRepository;
    private final StationRepository stationRepository;
    private final RentRepository rentRepository;
    private final RentalHistoryRepository rentalHistoryRepository;


    public List<Scooter>getAllScooters(){
return scooterRepository.findAll();
    }
    public void addScooter(Integer companyId, Scooter scooter) {
        Company company = companyRepository.findCompanyByCompanyId(companyId);
        if (company == null) {
            throw new ApiException("Company id not found!");
        }
        if (!company.getTransportType().equalsIgnoreCase("Scooter")){
            throw new ApiException("Company transport type is not set to bicycle");
        }
        scooter.setRentStatus("Not Rented");
        scooter.setCompany(company);

        scooterRepository.save(scooter);
    }

    public void updateScooter(Integer scooterId,Scooter newScooter){
        Scooter scooter=scooterRepository.findScooterByScooterId(scooterId);
        if(scooter==null){
            throw new ApiException("Scooter id not found!");
        }
        scooter.setLocation(newScooter.getLocation());
        scooter.setPinNumber(newScooter.getPinNumber());
        scooter.setFeatures(newScooter.getFeatures());
        scooter.setChargePercentage(newScooter.getChargePercentage());
        scooter.setMaxSpeed(newScooter.getMaxSpeed());
        scooter.setModel(newScooter.getModel());
        scooterRepository.save(scooter);
    }

    public void deleteScooter(Integer scooterId){
        Scooter scooter=scooterRepository.findScooterByScooterId(scooterId);
        if(scooter==null){
            throw new ApiException("Scooter id not found!");
        }
        scooterRepository.delete(scooter);
    }


    //Extra

    public List<Scooter>byModel(Integer model){
        List<Scooter> scooters=scooterRepository.findScooterByModel(model);
        if ((scooters.isEmpty())){
            throw new ApiException("not available");
        }
        return scooters;
    }


    public List<Scooter>viewAvalibleScooter(){
            List<Scooter> scooters=scooterRepository.findScooterByRentStatus("Not Rented");
        if(scooters.isEmpty()){
            throw new ApiException("No Soocter Avalabile");
        }
        return scooters;
    }

    public Double getAvgRating(String scooterName) {
        List<RentalHistory> rentalHistories = rentalHistoryRepository.findRentalHistoriesByTransportName(scooterName);
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
