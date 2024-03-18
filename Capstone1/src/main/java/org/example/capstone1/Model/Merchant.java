package org.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty(message = "ID Cannot be empty")
    private String id;

    @Size(min=3)
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
