package com.example.capstone3.Repository;

import com.example.capstone3.Model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station,Integer> {
    Station findStationByStationId(Integer stationId);

    @Query("select station from Station station where station.pickUpStation=?1 or station.dropOffStation=?1")
    Station getStationByStationName(String stationName);



    List<Station> findStationByStatus(String status);
    List<Station> findStationByHaveChargingStation(Boolean charging);



}
