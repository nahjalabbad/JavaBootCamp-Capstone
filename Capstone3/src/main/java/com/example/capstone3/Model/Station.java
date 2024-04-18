package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stationId;

    @Column(columnDefinition = "varchar(30)")
    private String dropOffStation;

    @Column(columnDefinition = "varchar(30)")
    private String pickUpStation;

    @Column(columnDefinition = "boolean default false")
    private Boolean haveChargingStation;

    @NotNull(message = "pickUpCapacity must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer pickUpCapacity;

    @NotNull(message = "DropOffCapacity must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer dropOffCapacity;

    @Pattern(regexp = "^(open|under maintenance|temporarily closed)")
    @Column(columnDefinition = "varchar(60) not null")
    private String status;

    @ManyToMany(mappedBy = "stations")
    private Set<Cars>cars;

    @ManyToMany(mappedBy = "stations")
    private Set<Bicycle>bicycles;

    @ManyToMany(mappedBy = "stations")
    private Set<Scooter>scooters;

    @ManyToMany
    @JsonIgnore
    private Set<Rent> rents;

    @OneToMany(mappedBy = "pickUpLocation")
    private List<Rent> rentsAsPickUp;

    @OneToMany(mappedBy = "dropOffLocation")
    private List<Rent> rentsAsDropOff;
}
