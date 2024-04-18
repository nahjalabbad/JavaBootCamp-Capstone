package com.example.capstone3.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Email
    @NotEmpty(message = "email should be not empty")
    @Column(columnDefinition = "varchar(60) not null unique")
    private String email ;

    @NotNull(message = "age should be not empty")
    @Column(columnDefinition = "int not null ")
    private Integer age;

    @NotNull(message = "phoneNumber should not be empty")
    @Column(columnDefinition = "int not null ")
    private Integer phoneNumber;

    @NotEmpty(message = "password should be not empty")
    @Size(min = 5 , message = "password should be more than 4 char")
    @Column(columnDefinition = "varchar(15) not null ")
    private String password ;

    @Column(columnDefinition = "int ")
    private Integer ride=0;

    @Column(columnDefinition = "int ")
    private Integer rating ;

    @Column(columnDefinition = "varchar(20) ")
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Rent> rentSet;

}
