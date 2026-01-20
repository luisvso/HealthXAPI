package br.healthx.Healthx.paciente.model.entity;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.dto.RequestPatientDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Your email should not be null")
    @Email(message = "Invalid Email format")
    private String email;

    @NotNull(message = "Your birthDate should not be null")
    private LocalDate birthDate;

    @NotNull(message = "Your name should not be null")
    private String name;

    @NotNull(message = "This filed should not be null")
    private Gender gender;

    @NotNull(message = "The phone should not be null")
    @Size(max = 15, message = "The phone number must hava at most 15 digits")
    private String phone;

    private String guardian;

    @NotNull(message = "This field should not be null")
    private Race race;

    private LocalDate createAt;

    public Patient() {
    }

    public Patient(RequestPatientDTO dto) {
        this.email = dto.email();
        this.birthDate = dto.birthDate();
        this.name = dto.name();
        this.gender = dto.gender();
        this.phone = dto.phone();
        this.guardian = dto.guardian();
        this.race = dto.race();
        this.createAt = LocalDate.now();
    }
}
