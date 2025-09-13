package br.healthx.Healthx.paciente.model.validation;

import org.springframework.context.annotation.Configuration;

import br.healthx.Healthx.paciente.dto.PacienteDTO;

@Configuration
public class PacienteValidation {

    public boolean existId(PacienteDTO dto) {
        return dto.id() != null;
    }

}
