package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RentalHistoryDTO {

    @NotNull(message = "rent id cannot be null")
    private Integer rentalId;

    private Integer userId;

    private String transportName;

    @Size(max = 30)
    private String PickUpStation;

    @Size(max = 30)
    private String DropOffStation;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String duration;

    @Pattern(regexp = "^(completed|on going)$")
    private String status;

    private Integer fuelLevel;

    private Integer rating ;

    private String comment;
}
