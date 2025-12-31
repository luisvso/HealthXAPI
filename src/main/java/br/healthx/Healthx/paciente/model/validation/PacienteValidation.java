package br.healthx.Healthx.paciente.model.validation;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Configuration;

import br.healthx.Healthx.paciente.dto.RequestPacienteDTO;
import br.healthx.Healthx.paciente.model.entity.Paciente;
import br.healthx.Healthx.paciente.model.exception.EmailAlreadyExistsException;
import br.healthx.Healthx.paciente.model.exception.InvalidBirthDateException;
import br.healthx.Healthx.paciente.model.exception.InvalidEmailFormatException;
import br.healthx.Healthx.paciente.model.exception.PacienteNotFoundException;
import br.healthx.Healthx.paciente.model.repository.PacienteRepository;

@Configuration
public class PacienteValidation {

    private final PacienteRepository pacienteRepository;

    public PacienteValidation(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void validatingPatient(RequestPacienteDTO dto) {
        invalidBirthDate(dto.dataNascimento());
        pacienteAlreadyExist(dto);
        // invalidEmailFormat(dto.email());
    }

    public void existId(Long id) {
        if (id == null) {
            throw new PacienteNotFoundException("This Paciente does not exist");
        }
    }

    public boolean existName(String name) {
        return name != null;
    }

    // public void invalidEmailFormat(String email) {
    // ^(?=.{1,64}@)[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)*@+[a-zA-Z0-9_-]+(\.+[a-zA-Z0-9_-]+)*$
    // boolean match =
    // Pattern.matches("^(?=.{1,64}@)[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)*@+[a-zA-Z0-9_-]+(\.+[a-zA-Z0-9_-]+)*$",
    // email);

    // if (!match) {
    // throw new InvalidEmailFormatException(email);
    // }
    // }

    public void invalidBirthDate(LocalDate date) {
        if (date.getYear() >= LocalDate.now().getYear()) {
            throw new InvalidBirthDateException(date);
        }
    }

    public void pacienteAlreadyExist(RequestPacienteDTO dto) {
        for (Paciente paciente : pacienteRepository.findAll()) {
            if (dto.email().equals(paciente.getEmail())) {
                throw new EmailAlreadyExistsException(dto.email());
            }
        }
    }
}
