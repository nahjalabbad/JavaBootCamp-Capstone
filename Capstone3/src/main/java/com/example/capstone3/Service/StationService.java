package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.Model.Rent;
import com.example.capstone3.Model.Station;
import com.example.capstone3.Repository.RentRepository;
import com.example.capstone3.Repository.StationRepository;
import com.example.capstone3.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;
    private final RentRepository rentRepository;

    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    public void addStation(Station station) {
        stationRepository.save(station);
    }

    public void updateStation(Integer stationId, Station station) {
        Station station1 = stationRepository.findStationByStationId(stationId);
        if (station1 == null) {
            throw new ApiException("rentalId not found");
        }
        station1.setPickUpStation(station.getPickUpStation());
        station1.setDropOffStation(station.getDropOffStation());
        station1.setHaveChargingStation(station.getHaveChargingStation());
        station1.setDropOffCapacity(station.getDropOffCapacity());
        station1.setPickUpCapacity(station.getPickUpCapacity());
        station1.setStatus(station.getStatus());

        stationRepository.save(station1);

    }

    public void deleteStation(Integer stationId) {
        Station station = stationRepository.findStationByStationId(stationId);
        if (station == null) {
            throw new ApiException("station id not found");
        }
        stationRepository.delete(station);
    }

    //Extra

    public String getStationStatus(String stationName){
        Station station=stationRepository.getStationByStationName(stationName);
        if (station == null) {
            throw new ApiException("station name not found");
        }
        return station.getStatus();
    }

    public List<Station> findStationByStatus(String status){
        List<Station> getStationsStatus=stationRepository.findStationByStatus(status);
        if (getStationsStatus.isEmpty()){
            throw new ApiException("No stations under the state "+status+" are available");
        }
        return getStationsStatus;
    }

    public List<Station> findStationByHaveChargingStation(Boolean charging){
        List<Station> getStationsWithCharging=stationRepository.findStationByHaveChargingStation(charging);
        if (getStationsWithCharging.isEmpty()){
            throw new ApiException("No stations under with charging feature are available");
        }
        return getStationsWithCharging;
    }


}
