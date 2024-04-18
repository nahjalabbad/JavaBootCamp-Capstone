package com.example.capstone3.Repository;

import com.example.capstone3.Model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars,Integer> {
    Cars findCarsByCarId(Integer carId);

   Cars findCarsByCarName(String carName);

    Boolean existsByCarName(String carName);
    List<Cars> findCarsByCarType (String carType);
    List<Cars> findCarsByRentStatus( String rentStatus);

}
