package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsService {
private final CarsRepository carsRepository;
private final CompanyRepository companyRepository;
private final StationRepository stationRepository;
    private final RentRepository rentRepository;
    private final RentalHistoryRepository rentalHistoryRepository;

public List<Cars>getAllCars(){
    return carsRepository.findAll();
}

    public void addCars(Integer companyId, Cars car) {
        Company company = companyRepository.findCompanyByCompanyId(companyId);
        if (company == null) {
            throw new ApiException("Company id not found!");
        }
        if (!company.getTransportType().equalsIgnoreCase("Car")) {
            throw new ApiException("Company transport type does not match");
        }

        if (car.getCarName() == null || car.getFuelPercentage() == null) {
            throw new ApiException("Car name and fuel percentage must be provided");
        }
        car.setCompany(company);
        carsRepository.save(car);
    }

    public  void updateCars(Integer carId,Cars NewCar){
    Cars car= carsRepository.findCarsByCarId(carId);
    if(car==null){
        throw new ApiException("Car id not found!");
    }
    car.setCarModel(NewCar.getCarModel());
    car.setLocation(NewCar.getLocation());
    car.setFuelPercentage(NewCar.getFuelPercentage());
    car.setNumSeats(NewCar.getNumSeats());
    car.setPinNumber(NewCar.getPinNumber());
    car.setCarType(NewCar.getCarType());
    carsRepository.save(car);
}


public void deleteCar(Integer carId){
    Cars car= carsRepository.findCarsByCarId(carId);
    if(car==null){
        throw new ApiException("Car id not found!");
    }
    carsRepository.delete(car);
}

//Extra

public List<Cars> viewCarsByType(String carType){
    List<Cars> c = carsRepository.findCarsByCarType(carType);
    if(c==null){
        throw new ApiException("Car id not found!");
    }
    return c;
}

public List<Cars> viewAllCars(){
        List<Cars> c = carsRepository.findCarsByRentStatus("not rented");
        if(c==null){
            throw new ApiException("Car id not found!");
        }
        return c;
    }

public Cars getSpecificDetails(String carName) {
    Cars car = carsRepository.findCarsByCarName(carName);
    if (car == null) {
        throw new ApiException("Car not found!");
    } else if (!car.getCarName().equalsIgnoreCase(carName)) {
        throw new ApiException("Car name mismatch!");
    }

    return car;
}



    public Double getAvgRating(String carName) {
        List<RentalHistory> rentalHistories = rentalHistoryRepository.findRentalHistoriesByTransportName(carName);
        double sum = 0;
        int count = 0;

        if (rentalHistories.isEmpty()) {
            throw new ApiException("No rental history found for this car name");
        }

        for (RentalHistory rh : rentalHistories) {
            sum += rh.getRating();
            count++;
        }

        return sum / count;
    }

}
