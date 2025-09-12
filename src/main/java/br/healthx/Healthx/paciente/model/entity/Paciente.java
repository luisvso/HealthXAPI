package br.healthx.Healthx.paciente.model.entity;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.dto.PacienteDTO;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataNascimento;
    private String nome;
    private String email;
    private String telefone;
    private String motivo;
    private String observacoes;

    public Paciente() {
    }

    public Paciente(PacienteDTO dto) {
        this.id = dto.id();
        this.dataNascimento = dto.dataNascimento();
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.motivo = dto.motivo();
        this.observacoes = dto.observacoes();
    }
}
