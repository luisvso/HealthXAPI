package br.healthx.Healthx.paciente.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PacienteContato {

    private String telefone;

    private String email;
}
