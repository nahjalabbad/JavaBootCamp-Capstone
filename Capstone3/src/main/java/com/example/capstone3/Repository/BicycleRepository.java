package com.example.capstone3.Repository;

import com.example.capstone3.Model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle,Integer> {
    Bicycle findBicycleByBicycleId(Integer id);

    List<Bicycle> findBicycleByNumOfWheels(Integer numOfweels);

    List<Bicycle> findBicycleByModel(Integer model);

    Bicycle findBicycleByBicycleName(String name);

    Boolean existsByBicycleName(String bicycleName);

    List<Bicycle> findBicycleByRentStatus(String rentStatus);
}
