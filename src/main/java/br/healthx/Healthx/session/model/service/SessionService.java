package br.healthx.Healthx.session.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.healthx.Healthx.session.dto.SessionRequestDTO;
import br.healthx.Healthx.session.dto.SessionResponseDTO;
import br.healthx.Healthx.session.mapper.SessionMapper;
import br.healthx.Healthx.session.model.entity.Session;
import br.healthx.Healthx.session.model.entity.SessionType;
import br.healthx.Healthx.session.model.entity.Status;
import br.healthx.Healthx.session.model.exception.ResourceNotFoundException;
import br.healthx.Healthx.session.model.repository.SessionRepository;
import br.healthx.Healthx.session.model.validation.SessionValidation;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final SessionValidation sessionValidation;

    public SessionService(SessionRepository sessionRepository, SessionMapper sessionMapper,
            SessionValidation sessionValidation) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.sessionValidation = sessionValidation;
    }

    public SessionResponseDTO createSession(SessionRequestDTO dto) {

        sessionValidation.validateSessionCreation(dto);

        Session session = sessionMapper.DtoToSession(dto);

        sessionRepository.save(session);

        return sessionMapper.sessionToDTO(session);
    }

    public SessionResponseDTO updateSession(Long id, SessionRequestDTO dto) {
        sessionValidation.validateSessionCreation(dto);
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        session = sessionRepository.save(sessionMapper.mapperUpateSession(session, dto));

        return sessionMapper.sessionToDTO(session);
    }

    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }

    public SessionResponseDTO findSession(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        return sessionMapper.sessionToDTO(session);
    }

    public List<SessionResponseDTO> findAllSessions() {
        return sessionMapper.sessionToListDTO(sessionRepository.findAll());
    }

    public List<SessionResponseDTO> findByStatus(Status status) {
        return sessionMapper.sessionToListDTO(sessionRepository.findByStatus(status));
    }

    public List<SessionResponseDTO> findBySessionType(SessionType sessionType) {
        return sessionMapper.sessionToListDTO(sessionRepository.findBySessionType(sessionType));
    }

    public List<SessionResponseDTO> findSessionByName(String name) {
        return sessionMapper.sessionToListDTO(sessionRepository.findByPatient_NameContainingIgnoreCase(name));
    }

    public List<SessionResponseDTO> findByStartDate(LocalDate start, LocalDate end) {
        return sessionMapper.sessionToListDTO(sessionRepository.findByStartDateBetween(start, end));
    }

}
