package br.healthx.Healthx.paciente.model.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.healthx.Healthx.paciente.dto.PacienteDTO;
import br.healthx.Healthx.paciente.model.repository.PacienteRepository;

@Configuration
public class PacienteValidation {

    @Autowired
    PacienteRepository pacienteRepository;

    public boolean existId(PacienteDTO dto) {
        return dto.id() != null;
    }

    public boolean existName(String name){
        return name != null;
    }

}
