package org.example.exercice3spring.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private UUID id;
    @NotNull(message = "Ce champ doit être rempli !")
    @NotBlank
    private String name;
    @NotNull(message = "Ce champ doit être rempli !")
    @NotBlank
    private String firstName;
    @Min(1)
    @Max(100)
    private Integer age;
    @NotNull(message = "Ce champ doit être rempli !")
    @NotBlank
    private String email;

}
