package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.RentalHistoryDTO;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final StationRepository stationRepository;
    private final RentalHistoryRepository rentalHistoryRepository;
    private final RentalHistoryService rentalHistoryService;
    private final CarsRepository carsRepository;
    private final ScooterRepository scooterRepository;
    private final BicycleRepository bicycleRepository;


    public List<Rent> getRent() {
        return rentRepository.findAll();
    }

    public String addRent(Integer userId, String companyName, String transName, String duration, Rent rent) {
        User user = userRepository.findUserByUserId(userId);
        Company company = companyRepository.findCompanyByCompanyName(companyName);
        Station station = stationRepository.getStationByStationName(rent.getPickUpLocation());

        if (user == null || company == null || station == null) {
            throw new ApiException("Invalid user, company, or station");
        }

        Rent rentToUse = null;

        if (carsRepository.existsByCarName(transName)&&rent.getTransportType().equalsIgnoreCase("Car")) {
            Cars car = carsRepository.findCarsByCarName(transName);
            rentToUse = createRent(car, transName, duration, rent);
        }
        else if (bicycleRepository.existsByBicycleName(transName)&&rent.getTransportType().equalsIgnoreCase("Bicycle")) {
            Bicycle bicycle = bicycleRepository.findBicycleByBicycleName(transName);
            rentToUse = createRent(bicycle, transName, duration, rent);
        }
        else if (scooterRepository.existsByScooterName(transName)&&rent.getTransportType().equalsIgnoreCase("Scooter")) {
            Scooter scooter = scooterRepository.findScooterByScooterName(transName);
            rentToUse = createRent(scooter, transName, duration, rent);
        } else {
            throw new ApiException("please check transport name and type if they match");
        }

        company.setQuantity(company.getQuantity() - rent.getQuantity());

        user.setRide(user.getRide() + 1);
        user.getRentSet().add(rentToUse);

        station.setPickUpCapacity(station.getPickUpCapacity() - rent.getQuantity());
        station.setDropOffCapacity(station.getDropOffCapacity() - rent.getQuantity());

        rentToUse.setRentStatus("Rented");
        rentToUse.setDuration(duration);
        rentToUse.setUser(user);

        RentalHistoryDTO rentalHistoryDTO = new RentalHistoryDTO(null, user.getUserId(), rentToUse.getTransportName(),
                rentToUse.getPickUpLocation(), rentToUse.getDropOffLocation(), rentToUse.getStartDate(),
                rentToUse.getEndDate(), rentToUse.getDuration(), "on going", rentToUse.getFuelPercentage(), null, null);
        RentalHistory rentalHistory = rentalHistoryService.addRentalHistory(rentToUse.getRentId(), rentalHistoryDTO);

        companyRepository.save(company);
        userRepository.save(user);
        rentRepository.save(rentToUse);
        stationRepository.save(station);
        rentalHistoryRepository.save(rentalHistory);

        return "Thank you for renting your pin number is " + rentToUse.getPinNumber();
    }

    public void updateRent(Integer rentId, Rent rent) {
        Rent r = rentRepository.findRentByRentId(rentId);
        if (r == null) {
            throw new ApiException("Rent not found");
        }
        r.setDropOffLocation(rent.getDropOffLocation());
        r.setPickUpLocation(rent.getPickUpLocation());
        r.setTransportType(rent.getTransportType());
        r.setRentStatus(rent.getRentStatus());
        r.setReturnStatus(rent.getReturnStatus());
        r.setStartDate(rent.getStartDate());
        r.setEndDate(rent.getEndDate());
        r.setDuration(rent.getDuration());
        rentRepository.save(r);
    }

    public void deleteRent(Integer rentId) {
        Rent r = rentRepository.findRentByRentId(rentId);
        if (r == null) {
            throw new ApiException("Rent not found");
        }
        rentRepository.delete(r);
    }

    //Extra

    private Rent createRent(Object transport, String transName, String duration, Rent rent) {
        if (transport == null) {
            throw new ApiException("Transport object is null");
        }
        Station pickUpStation = stationRepository.getStationByStationName(rent.getPickUpLocation());
        Station dropOffStation = stationRepository.getStationByStationName(rent.getDropOffLocation());



        Rent rentToUse = new Rent();
        rentToUse.setQuantity(1);
        rentToUse.setRentStatus("Not Rented");
        rentToUse.setDuration(duration);

        if (transport instanceof Cars) {
            Cars car = (Cars) transport;
            rentToUse.setTransportName(car.getCarName());
            rentToUse.setFuelPercentage(car.getFuelPercentage());
            rentToUse.setTransportType("Car");
            rentToUse.setPickUpLocation(car.getLocation());
            rentToUse.setPrice(car.getPrice());
            rentToUse.setPinNumber(car.getPinNumber());

            rentRepository.save(rentToUse);

            car.getStations().add(pickUpStation);
            car.getRents().add(rentToUse);
            car.setRentStatus(rentToUse.getRentStatus());
            carsRepository.save(car);
        } else if (transport instanceof Bicycle) {
            Bicycle bicycle = (Bicycle) transport;
            rentToUse.setTransportName(bicycle.getBicycleName());
            rentToUse.setFuelPercentage(0);
            rentToUse.setTransportType("Bicycle");
            rentToUse.setPrice(bicycle.getPrice());
            rentToUse.setPinNumber(bicycle.getPinNumber());

            rentRepository.save(rentToUse);

            bicycle.getStations().add(pickUpStation);
            bicycle.getRents().add(rentToUse);
            bicycle.setRentStatus(rentToUse.getRentStatus());
            bicycleRepository.save(bicycle);
        } else if (transport instanceof Scooter) {
            Scooter scooter = (Scooter) transport;
            rentToUse.setTransportName(scooter.getScooterName());
            rentToUse.setFuelPercentage(scooter.getChargePercentage());
            rentToUse.setTransportType("Scooter");
            rentToUse.setPrice(scooter.getPrice());
            rentToUse.setPinNumber(scooter.getPinNumber());

            rentRepository.save(rentToUse);

            scooter.getStations().add(pickUpStation);
            scooter.getRents().add(rentToUse);
            scooter.setRentStatus(rentToUse.getRentStatus());
            scooterRepository.save(scooter);
        } else {
            throw new ApiException("Invalid transport type");
        }

        Integer pricePerDuration=pricePerDuration(duration, rentToUse.getPrice());

        rentToUse.setPickUpLocation(rent.getPickUpLocation());
        rentToUse.setDropOffLocation(rent.getDropOffLocation());
        rentToUse.setStartDate(rent.getStartDate());
        rentToUse.setEndDate(rent.getEndDate());
        rentToUse.setDuration(rent.getDuration());
        rentToUse.setPrice(pricePerDuration);
        rentToUse.setPickUpStation(pickUpStation);
        rentToUse.setDropOffStation(dropOffStation);


        rentRepository.save(rentToUse);

        return rentToUse;
    }

    public void returnRent(Integer userId, String companyName, Integer rentId) {
        User user = userRepository.findUserByUserId(userId);
        Rent rent = rentRepository.findRentByRentId(rentId);
        Company company = companyRepository.findCompanyByCompanyName(companyName);

        if (user == null || company == null || rent == null) {
            throw new ApiException("Invalid user, company, or rent");
        }

        company.setQuantity(company.getQuantity() + rent.getQuantity());
        rent.setRentStatus("Not Rented");

        if (rent.getFuelPercentage() == 0) {
            throw new ApiException("The fuel is already at 0");
        } else {
            rent.setFuelPercentage(rent.getFuelPercentage() - 20);
        }

        companyRepository.save(company);
        rentRepository.save(rent);

        RentalHistory existingRentalHistory = rentalHistoryRepository.findRentalHistoriesByRent(rent);
        if (existingRentalHistory != null) {
            existingRentalHistory.setStatus("completed");
            existingRentalHistory.setFuelLevel(rent.getFuelPercentage());
            rentalHistoryRepository.save(existingRentalHistory);
        } else {
            RentalHistory rentalHistory = new RentalHistory(
                    null, userId, rent.getTransportName(), rent.getPickUpLocation(),
                    rent.getDropOffLocation(), rent.getStartDate(), rent.getEndDate(), rent.getDuration(),
                    "completed", rent.getFuelPercentage(), null, null, rent
            );
            rentalHistoryRepository.save(rentalHistory);
        }
    }




    public List<Rent>byAvalablity(){
        List<Rent> avalabiles = rentRepository.findRentByTransportType("available");
        if (avalabiles.isEmpty()){
            throw new ApiException("not available");
        }
        return avalabiles;
    }

    private Integer pricePerDuration(String duration,Integer price) {
        Rent rent=rentRepository.getRentByPrice(price);
        switch (duration) {
            case "1 Day":
                rent.setPrice(rent.getPrice());
                break;
            case "3 Days":
                rent.setPrice(rent.getPrice()*3);
                break;
            case "1 Week":
                rent.setPrice(rent.getPrice()*7);
                break;
            case "3 Weeks":
                rent.setPrice(rent.getPrice()*21);
                break;
            case "1 Month":
                rent.setPrice(rent.getPrice()*30);
                break;
            default:
                throw new IllegalArgumentException("Invalid duration: " + duration);
        }
        return rent.getPrice();
    }

}









