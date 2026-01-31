package br.healthx.Healthx.session.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.healthx.Healthx.session.dto.SessionRequestDTO;
import br.healthx.Healthx.session.dto.SessionResponseDTO;
import br.healthx.Healthx.session.model.entity.Session;

@Component
public class SessionMapper {

    public Session DtoToSession(SessionRequestDTO dto) {
        Session ss = new Session();

        ss.setStartDate(dto.startDate());
        ss.setEndDate(dto.endDate());
        ss.setPatient(dto.patient());
        ss.setNotes(dto.notes());
        ss.setStatus(dto.status());
        ss.setSessionType(dto.sessionType());
        ss.setSessionComplaint(dto.sessionComplaint());
        ss.setPsychologist(dto.psychologist());
        ss.setStartTime(dto.startTime());
        ss.setEndTime(dto.endTime());

        return ss;
    }

    public Session mapperUpdateSession(Session session, SessionRequestDTO dto) {
        session.setEndDate(dto.endDate());
        session.setNotes(dto.notes());
        session.setPatient(dto.patient());
        session.setPsychologist(dto.psychologist());
        session.setSessionComplaint(dto.sessionComplaint());
        session.setSessionType(dto.sessionType());
        session.setStartDate(dto.startDate());
        session.setStatus(dto.status());
        session.setStartTime(dto.startTime());
        session.setEndTime(dto.endTime());

        return session;
    }

    public SessionResponseDTO sessionToDTO(Session ss) {
        SessionResponseDTO sessionResponseDTO = new SessionResponseDTO(ss.getId(), ss.getStartDate(), ss.getEndDate(),
                ss.getPatient(), ss.getNotes(), ss.getStatus(), ss.getSessionType(), ss.getSessionComplaint(),
                ss.getPsychologist(), ss.getStartTime(), ss.getEndTime());

        return sessionResponseDTO;
    }

    public Page<SessionResponseDTO> sessionToListDTO(Page<Session> sessions) {

        return sessions.map(this::sessionToDTO);

    }

}
