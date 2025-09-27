package br.healthx.Healthx.paciente.model.entity;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.dto.PacienteDTO;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private String nome;

    @ManyToOne
    private Genero genero;

    @NotNull
    private String responsavel;

    @NotNull
    private String raca;

    @Embedded
    private PacienteContato pacienteContato;

    @Embedded
    private PacienteClinico pacienteClinico;

    // @NotNull
    // private Psicologo PsicologoId;

    public Paciente() {
    }

    public Paciente(PacienteDTO dto) {
        this.id = dto.id();
        this.dataNascimento = dto.dataNascimento();
        this.nome = dto.nome();
        this.genero = dto.genero();
        this.responsavel = dto.responsavel();
        this.raca = dto.raca();
        this.pacienteClinico = dto.pacienteClinico();
        this.pacienteContato = dto.pacienteContato();
    }
}
