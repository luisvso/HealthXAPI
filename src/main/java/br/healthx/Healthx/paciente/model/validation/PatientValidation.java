package br.healthx.Healthx.paciente.model.validation;

import java.time.LocalDate;

import org.springframework.context.annotation.Configuration;

import br.healthx.Healthx.paciente.dto.RequestPatientDTO;
import br.healthx.Healthx.paciente.model.entity.Patient;
import br.healthx.Healthx.paciente.model.exception.EmailAlreadyExistsException;
import br.healthx.Healthx.paciente.model.exception.InvalidBirthDateException;
import br.healthx.Healthx.paciente.model.exception.PacienteNotFoundException;
import br.healthx.Healthx.paciente.model.repository.PatientRepository;

@Configuration
public class PatientValidation {

    private final PatientRepository patientRepository;

    public PatientValidation(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void validatingPatient(RequestPatientDTO dto) {
        invalidBirthDate(dto.birthDate());
        patientAlreadyExist(dto);
        // invalidEmailFormat(dto.email());
    }

    public void existId(Long id) {
        if (patientRepository.findById(id).isEmpty()) {
            throw new PacienteNotFoundException(id);
        }
    }

    public boolean existName(String name) {
        return name != null;
    }

    public void invalidBirthDate(LocalDate date) {
        if (date.getYear() > LocalDate.now().getYear()) {
            throw new InvalidBirthDateException(date);
        }
    }

    public void patientAlreadyExist(RequestPatientDTO dto) {
        for (Patient patient : patientRepository.findAll()) {
            if (dto.email().equals(patient.getEmail())) {
                throw new EmailAlreadyExistsException(dto.email());
            }
        }
    }
}
