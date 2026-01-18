package br.healthx.Healthx.psychologist.model.validation;

import org.springframework.stereotype.Component;

import br.healthx.Healthx.psychologist.dto.PsychologistRequestDTO;
import br.healthx.Healthx.psychologist.model.entity.Psychologist;
import br.healthx.Healthx.psychologist.model.exception.PsychologistAlreadyExistsException;
import br.healthx.Healthx.psychologist.model.repository.PsychologistRepository;

@Component
public class PsychologistValidation {

    private final PsychologistRepository psychologistRepository;

    public PsychologistValidation(PsychologistRepository psychologistRepository) {
        this.psychologistRepository = psychologistRepository;
    }

    public void validatePsychologist(PsychologistRequestDTO dto) {
        alreadyExistsPsychologist(dto);
    }

    public void alreadyExistsPsychologist(PsychologistRequestDTO dto) {
        for (Psychologist psychologist : psychologistRepository.findAll()) {
            if (dto.email().equals(psychologist.getEmail()) && !dto.CRP().equals(psychologist.getCRP())
                    && !dto.phone().equals(psychologist.getPhone()))
                throw new PsychologistAlreadyExistsException("This email is already registered");
        }
    }
}
