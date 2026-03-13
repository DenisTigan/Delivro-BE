package com.example.Delivro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest (

    @NotBlank(message = "Email-ul este obligatoriu!")
    @Email(message = "Te rog introdu o adresa de email valida!")
    String email,

    @NotBlank(message = "Parola este obligatorie!")
    @Size(min = 6, message = "Parola trebuie sa aibe minim 6 caratere!")
    String password,

    @NotBlank(message = "Numele este obligatoriu!")
    String firstName,

    @NotBlank(message = "Prenumele este obligatoriu!")
    String lastName,

    String phone
    ){
}
