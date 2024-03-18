package org.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "ID Cannot be empty")
    private String id;

    @NotEmpty(message = "Username Cannot be empty")
    @Size(min = 5)
    private String username;

    @NotEmpty(message = "Username Cannot be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$")
    private String password;

    @Email
    @NotEmpty(message = "Email Cannot be empty")
    private String email;

    @NotEmpty(message = "Role Cannot be empty")
    @Pattern(regexp = "^(Admin|Customer)$")
    private String role;

    @NotNull(message = "Balance cannot be empty")
    @Positive
    private double balance;

    private int points;





}
