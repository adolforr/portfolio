package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id; // Clave primaria

    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstName;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;
    @NotBlank(message = "El título no puede estar vacío")
    private String title; // Ej: "Full Stack Developer"
    @NotBlank(message = "La descripción del perfil no puede estar vacía")
    private String profileDescription; // El texto largo del "Who am I?"
    @NotBlank(message = "La imagen no puede estar vacía")
    private String profileImageUrl; // URL o ruta a la imagen de perfil
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private Integer yearsOfExperience;
    @Email(message = "El email no es válido")
    private String email;
    @NotBlank(message = "El teléfono no puede estar vacío")
    private String phone;
    @URL(message = "LinkedIn es un red obligatoria")
    private String linkedinUrl;
    @URL(message = "GitHub es una red obligatoria")
    private String githubUrl;
}
