package com.example.capstone3.Model;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    @NotEmpty(message = "car type must be not empty")
    @Pattern(regexp = "^(luxury|sedan|sport)")
    @Column(columnDefinition = "varchar(20)")
    private String carType;

    @NotEmpty(message = "car name must be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String carName;

    @NotNull(message = "car model must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer carModel;

    @NotNull(message = "fuel Percentage must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer fuelPercentage;

    @NotEmpty(message = "location must be not empty ")
    @Column(columnDefinition = "varchar(250) not null")
    private String location;

    @NotNull(message = "number of seats must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer numSeats;

    @NotNull(message = "quantity must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer price;

    @Column(columnDefinition = "int unique")
    private Integer pinNumber;

    @Column(columnDefinition = "varchar(20)")
    private String rentStatus;

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")
    @JsonIgnore
    private Company company;


    @ManyToMany
    @JsonIgnore
    private Set<Station>stations;

    @ManyToMany
    @JsonIgnore
    private Set<Rent> rents;

}
