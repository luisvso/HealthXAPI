package br.healthx.Healthx.paciente.model.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.healthx.Healthx.paciente.model.exception.PacienteNaoExisteException;
import br.healthx.Healthx.paciente.model.repository.PacienteRepository;

@Configuration
public class PacienteValidation {

    @Autowired
    PacienteRepository pacienteRepository;

    public void existId(Long id) {
        if (id == null) {
            throw new PacienteNaoExisteException("Este paciente não existe");
        }
    }

    public boolean existName(String name) {
        return name != null;
    }

}
