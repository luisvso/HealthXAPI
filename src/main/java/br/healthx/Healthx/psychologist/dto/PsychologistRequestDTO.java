package br.healthx.Healthx.psychologist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PsychologistRequestDTO(
        @Email(message = "Invalid email format") @NotBlank(message = "Your Email must not be blank") String email,
        @NotBlank(message = "The Phone") String phone,
        @NotBlank(message = "The name must not be blank") String name,
        @NotBlank(message = "The CRP must not be blank") String CRP) {
}
