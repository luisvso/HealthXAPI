package br.healthx.Healthx.paciente.dto;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.model.entity.Gender;
import br.healthx.Healthx.paciente.model.entity.Race;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record RequestPatientDTO(
        @NotNull(message = "Your name should not be null") String name,
        @NotNull(message = "Your birthDate should not be null") LocalDate birthDate,
        @NotNull(message = "This filed should not be null") Gender gender,
        String guardian,
        @NotNull(message = "This field should not be null") Race race,
        @NotNull(message = "Your email should not be null") @Email(message = "Invalid Email format") String email,
        @NotNull(message = "The phone should not be null") @Size(max = 15, message = "The phone number must hava at most 15 digits") String phone) {
}
