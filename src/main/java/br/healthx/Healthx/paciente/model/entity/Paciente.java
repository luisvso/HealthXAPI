package br.healthx.Healthx.paciente.model.entity;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.dto.RequestPacienteDTO;
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
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Your birth date should not be null")
    @Email(message = "Invalid Email format")
    private String email;

    @NotNull(message = "Your name should not be null")
    private LocalDate dataNascimento;

    @NotNull(message = "This field should not be null")
    private String name;

    @NotNull(message = "This filed should not be null")
    private Genero genero;

    @NotNull
    @Size(max = 15, message = "The phone number must hava at most 15 digits")
    private String telefone;

    private String responsavel;

    @NotNull(message = "This field should not be null")
    private Raca raca;

    private LocalDate criadoEm;

    public Paciente() {
    }

    public Paciente(RequestPacienteDTO dto) {
        this.email = dto.email();
        this.dataNascimento = dto.dataNascimento();
        this.name = dto.name();
        this.genero = dto.genero();
        this.telefone = dto.telefone();
        this.responsavel = dto.responsavel();
        this.raca = dto.raca();
        this.criadoEm = LocalDate.now();
    }
}
