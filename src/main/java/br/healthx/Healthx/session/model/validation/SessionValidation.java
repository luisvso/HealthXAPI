package br.healthx.Healthx.session.model.validation;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.healthx.Healthx.session.dto.SessionRequestDTO;
import br.healthx.Healthx.session.model.exception.SessionDateException;

@Component
public class SessionValidation {

    public void validateSessionCreation(SessionRequestDTO dto) {
        validateDates(dto);
    }

    public boolean ExistSessionId(Long id) {
        return (id == null);
    }

    private void validateDates(SessionRequestDTO dto) {
        if (dto.startDate().isBefore(LocalDate.now()) || dto.endDate().isBefore(dto.startDate())) {
            throw new SessionDateException("The date is invalid");
        }
    }
}
