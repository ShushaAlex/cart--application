package org.example.cartapplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCreateRequestDto(
        @NotBlank
        String username,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
                message = "password must be between 8 and 20 uppercase and lowercase symbols, digit and special symbols '@#$%^&+='"
        )
        String password,

        @NotBlank
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
                message = "password must be between 8 and 20 uppercase and lowercase symbols, digit and special symbols '@#$%^&+='"
        )
        String passwordConfirmation) {

}