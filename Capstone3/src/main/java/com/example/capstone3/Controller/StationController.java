package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.Model.Rent;
import com.example.capstone3.Model.Station;
import com.example.capstone3.Service.RentService;
import com.example.capstone3.Service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/station")
@RequiredArgsConstructor
public class StationController {
    private final StationService stationService;

    @GetMapping("/get")
    public ResponseEntity getStations(){
        return ResponseEntity.status(200).body(stationService.getStations());
    }

    @PostMapping("/add")
    public ResponseEntity addStation(@RequestBody @Valid Station station ){
        stationService.addStation(station);
        return ResponseEntity.status(200).body(new ApiResponse("Station Added"));
    }

    @PutMapping("/update/{stationId}")
    public ResponseEntity updateStation( @PathVariable Integer stationId, @RequestBody @Valid Station station ){
        stationService.updateStation(stationId,station);
        return ResponseEntity.status(200).body(new ApiResponse("Station Updated"));
    }

    @DeleteMapping("/delete/{stationId}")
    public ResponseEntity deleteStation(@PathVariable Integer stationId ){
        stationService.deleteStation(stationId);
        return ResponseEntity.status(200).body(new ApiResponse("Station Deleted"));
    }


    //              EXTRA

    @GetMapping("/get-astation-status/{stationName}")
    public ResponseEntity getStationStatus(@PathVariable String stationName ){
        return ResponseEntity.status(200).body(stationService.getStationStatus(stationName));
    }

    @GetMapping("/get-stations-status/{status}")
    public ResponseEntity findStationByStatus(@PathVariable String status ){
        return ResponseEntity.status(200).body(stationService.findStationByStatus(status));
    }

    @GetMapping("/get-station-charging/{charging}")
    public ResponseEntity getStationWithCharging(@PathVariable Boolean charging ){
        return ResponseEntity.status(200).body(stationService.findStationByHaveChargingStation(charging));
    }
}
