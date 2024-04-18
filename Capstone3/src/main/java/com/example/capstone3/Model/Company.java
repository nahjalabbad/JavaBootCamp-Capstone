package com.example.capstone3.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    @NotEmpty(message = "companyName should be not empty")
    @Column(columnDefinition = "varchar(40) unique not null")
    private String companyName ;

    @NotNull(message = "quantity should not be empty")
    @Column(columnDefinition = "int not null ")
    private Integer quantity;

    @Column(columnDefinition = "varchar(50)")
    private String transportType;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "company")
    private Set<Cars> cars;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "company")
    private Set<Bicycle> bicycles;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "company")
    private Set<Scooter> scooters;

}
