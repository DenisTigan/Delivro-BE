package com.example.Delivro.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "Email-ul nu poate fi gol!")
        String email,

        @NotBlank(message = "Parola nu poate fi goala!")
        String password
) {
}
