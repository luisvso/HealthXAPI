package br.healthx.Healthx.session.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.healthx.Healthx.session.dto.SessionRequestDTO;
import br.healthx.Healthx.session.dto.SessionResponseDTO;
import br.healthx.Healthx.session.mapper.SessionMapper;
import br.healthx.Healthx.session.model.entity.Session;
import br.healthx.Healthx.session.model.entity.SessionType;
import br.healthx.Healthx.session.model.entity.Status;
import br.healthx.Healthx.session.model.exception.ResourceNotFoundException;
import br.healthx.Healthx.session.model.repository.SessionRepository;
import br.healthx.Healthx.session.model.validation.SessionValidation;

@Service
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

    @Transactional(timeout = 10)
    public SessionResponseDTO createSession(SessionRequestDTO dto) {

        sessionValidation.validateSession(dto);

        Session session = sessionMapper.DtoToSession(dto);

        sessionRepository.save(session);

        return sessionMapper.sessionToDTO(session);
    }

    @Transactional(timeout = 10)
    public SessionResponseDTO updateSession(Long id, SessionRequestDTO dto) {
        sessionValidation.validateSession(dto);
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        session = sessionRepository.save(sessionMapper.mapperUpateSession(session, dto));

        return sessionMapper.sessionToDTO(session);
    }

    @Transactional(timeout = 10)
    public void deleteSession(Long id) throws NoSuchElementException {
        if (!sessionValidation.ExistSessionId(id))
            throw new NoSuchElementException("The Session of id : " + id + " does not exist");
        sessionRepository.deleteById(id);

    }

    @Transactional(readOnly = true, timeout = 10)
    public SessionResponseDTO findSession(Long id) {
        sessionValidation.validateFields(id);

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        return sessionMapper.sessionToDTO(session);
    }

    @Transactional(readOnly = true, timeout = 10)
    public List<SessionResponseDTO> findAllSessions() {
        return sessionMapper.sessionToListDTO(sessionRepository.findAll());
    }

    @Transactional(readOnly = true, timeout = 10)
    public List<SessionResponseDTO> findByStatus(Status status) {
        sessionValidation.validateFields(status);

        return sessionMapper.sessionToListDTO(sessionRepository.findByStatus(status));
    }

    @Transactional(readOnly = true, timeout = 10)
    public List<SessionResponseDTO> findBySessionType(SessionType sessionType) {
        sessionValidation.validateFields(sessionType);

        return sessionMapper.sessionToListDTO(sessionRepository.findBySessionType(sessionType));
    }

    @Transactional(readOnly = true, timeout = 10)
    public List<SessionResponseDTO> findSessionByName(String name) {
        sessionValidation.validateFields(name);

        return sessionMapper.sessionToListDTO(sessionRepository.findByPatient_NameContainingIgnoreCase(name));
    }

    @Transactional(readOnly = true, timeout = 10)
    public List<SessionResponseDTO> findByStartDate(LocalDate start, LocalDate end) {
        sessionValidation.validateFields(start, end);

        return sessionMapper.sessionToListDTO(sessionRepository.findByStartDateBetween(start, end));
    }

}
