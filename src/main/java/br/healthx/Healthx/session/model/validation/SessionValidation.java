package br.healthx.Healthx.session.model.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import br.healthx.Healthx.session.dto.SessionRequestDTO;
import br.healthx.Healthx.session.dto.SessionUpdateDTO;
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

        List<Session> sessions = sessionRepository.findByStatusAndDate(Status.SCHEDULED, dto.date());

        hourValid(dto.startTime(), dto.endTime());

        for (Session session : sessions) {
            if (validateDates(dto, session.getDate())
                    && hourConflict(dto.startTime(), dto.endTime(), session.getStartTime(), session.getEndTime()))
                throw new SessionHourException(
                        "The hour that start at: " + session.getStartTime() + " and end at: " + session.getEndTime()
                                + " is already alocated");
        }

    }

    public boolean ExistSessionId(Long id) {
        return (id == null);
    }

    private boolean validateDates(SessionRequestDTO dto, LocalDate dateAlocated) {
        return (dto.date().equals(dateAlocated));
    }

    public boolean validateFields(Object... objects) {
        for (Object o : objects) {
            if (o == null)
                return true;
        }
        return false;
    }

    public boolean hourConflict(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return (start1.isBefore(end2) && end1.isAfter(start2));
    }

    public void hourValid(LocalTime start, LocalTime end) {

        if (end.isBefore(start))
            throw new SessionHourException("The end Time:" + end + " should be after: " + start);
    }
}
