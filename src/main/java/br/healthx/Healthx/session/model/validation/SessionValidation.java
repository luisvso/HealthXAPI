package br.healthx.Healthx.session.model.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import br.healthx.Healthx.session.dto.SessionRequestDTO;
import br.healthx.Healthx.session.model.entity.Session;
import br.healthx.Healthx.session.model.entity.Status;
import br.healthx.Healthx.session.model.exception.SessionDateException;
import br.healthx.Healthx.session.model.exception.SessionHourException;
import br.healthx.Healthx.session.model.repository.SessionRepository;

@Component
public class SessionValidation {

    public SessionRepository sessionRepository;

    public SessionValidation(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void validateSession(SessionRequestDTO dto) {

        List<Session> sessions = sessionRepository.findByStatusAndStartDate(Status.SCHEDULED, dto.startDate());

        validateDates(dto);
        hourValid(dto.startTime(), dto.endTime());

        for (Session session : sessions) {
            hourConflict(dto.startTime(), dto.endTime(), session.getStartTime(), session.getEndTime());
        }

    }

    public boolean ExistSessionId(Long id) {
        return (id == null);
    }

    private void validateDates(SessionRequestDTO dto) {
        if (dto.startDate().isBefore(LocalDate.now()) || dto.endDate().isBefore(dto.startDate())) {
            throw new SessionDateException("The date is invalid");
        }
    }

    public boolean validateFields(Object... objects) {
        for (Object o : objects) {
            if (o == null)
                return true;
        }
        return false;
    }

    public void hourConflict(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        if (start1.isBefore(end2) && end1.isAfter(start2))
            throw new SessionHourException(
                    "The hour that start at: " + start1 + " and end at: " + end1 + " is already alocated");
    }

    public void hourValid(LocalTime start, LocalTime end) {

        if (end.isBefore(start))
            throw new SessionHourException("The end Time:" + end + " should be after: " + start);
    }
}
