package com.example.capstone3.Repository;

import com.example.capstone3.Model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter,Integer> {
    Scooter findScooterByScooterId(Integer id);
    Scooter findScooterByScooterName(String name);
    Boolean existsByScooterName(String scooterName);
    List<Scooter> findScooterByModel(Integer model);
    List<Scooter>  findScooterByRentStatus(String status);
}
