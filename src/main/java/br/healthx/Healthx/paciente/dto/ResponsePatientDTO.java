package br.healthx.Healthx.paciente.dto;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.model.entity.Gender;
import br.healthx.Healthx.paciente.model.entity.Race;

public record ResponsePatientDTO(
        String name,
        LocalDate birthDate,
        Gender gender,
        String guardian,
        Race race,
        String email,
        String phone,
        LocalDate createAt) {
}
