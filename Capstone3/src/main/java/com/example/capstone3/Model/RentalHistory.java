package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class RentalHistory {

    @Id
    private Integer rentalId;

    @Column(columnDefinition = "int")
    private Integer userId;

    @Column(columnDefinition = "varchar(30)")
    private String transportName;

    @Column(columnDefinition = "varchar(30)")
    private String PickUpStation;

    @Column(columnDefinition = "varchar(30)")
    private String DropOffStation;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime startTime;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime endTime;

    @Column(columnDefinition = "varchar(30)")
    private String duration;

    @Column(columnDefinition = "varchar(30)")
    private String status;

    @Column(columnDefinition = "int")
    private Integer fuelLevel;

    @Column(columnDefinition = "int ")
    private Integer rating ;

    @Column(columnDefinition = "varchar(20) ")
    private String comment;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Rent rent;



}
