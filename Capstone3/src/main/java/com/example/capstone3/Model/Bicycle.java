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
@Table(name = "bicycle")
public class Bicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bicycleId;

    @NotNull(message = "model must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer model;

    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(20) unique ")
    private String bicycleName;

    @NotEmpty(message = "feature must be not empty")
    @Column(columnDefinition = "varchar(100) not null ")
    private String features;

    @NotNull(message = "number Of Wheels must be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer numOfWheels;

    @Column(columnDefinition = "int unique")
    private Integer pinNumber;

    @NotEmpty(message = "location must be not empty ")
    @Column(columnDefinition = "varchar(250) not null")
    private String location;

    @NotNull(message = "price must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer price;

    @Pattern(regexp = "^(Rented|Not Rented|Not Available)$",  message = "rentStatus must be either Rented, Not Rented, or Not Available")
    @Column(columnDefinition = "varchar(20)")
    private String rentStatus;

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
