package com.example.capstone3.Model;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scooterId;

    @NotNull(message = "model must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer model;

    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(20) unique ")
    private String scooterName;

    @NotEmpty(message = "Features must be not empty")
    @Column(columnDefinition = "varchar(100) not null ")
    private String features;

    @NotNull(message = "max speed must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer maxSpeed;

    @NotEmpty(message = "location must be not empty ")
    @Column(columnDefinition = "varchar(250) not null")
    private String location;

    @NotNull(message = "charge Percentage must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer chargePercentage;

    @Column(columnDefinition = "int unique")
    private Integer pinNumber;

    @Pattern(regexp = "^(Rented|Not Rented|Not Available)$",  message = "rentStatus must be either Rented, Not Rented, or Not Available")
    @Column(columnDefinition = "varchar(20)")
    private String rentStatus;

    @NotNull(message = "price must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer price;

    @Pattern(regexp = "^(Returned|Not Returned)$",  message = "returnStatus must be either Returned or Not Returned")
    @Column(columnDefinition = "varchar(50)")
    private String returnStatus;

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")
    @JsonIgnore
    private Company company;


    @ManyToMany
    @JsonIgnore
    private Set<Station> stations;


    @ManyToMany
    @JsonIgnore
    private Set<Rent> rents;
}
