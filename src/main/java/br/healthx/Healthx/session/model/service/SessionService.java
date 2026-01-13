package br.healthx.Healthx.session.model.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        session = sessionRepository.save(sessionMapper.mapperUpdateSession(session, dto));

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
        if (sessionValidation.validateFields(id))
            throw new ResourceNotFoundException("This Resource does not exit");

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        return sessionMapper.sessionToDTO(session);
    }

    @Transactional(readOnly = true, timeout = 10)
    public Page<SessionResponseDTO> findAllSessions(Pageable pageable) {

        return sessionMapper.sessionToListDTO(sessionRepository.findAll(pageable));

        // return sessionRepository
        // .findAll(pageable)
        // .map(sessionMapper::sessionToDTO);

        // return sessionMapper.sessionToListDTO(sessionRepository.findAll(pageable));
    }

    @Transactional(readOnly = true, timeout = 10)
    public Page<SessionResponseDTO> findByStatus(Status status, Pageable pageable) {
        if (sessionValidation.validateFields(status))
            throw new ResourceNotFoundException("This Resource does not exit");

        return sessionMapper.sessionToListDTO(sessionRepository.findByStatus(status, pageable));
    }

    @Transactional(readOnly = true, timeout = 10)
    public Page<SessionResponseDTO> findBySessionType(SessionType sessionType, Pageable pageable) {
        if (sessionValidation.validateFields(sessionType))
            throw new ResourceNotFoundException("This Resource does not exit");

        return sessionMapper.sessionToListDTO(sessionRepository.findBySessionType(sessionType, pageable));
    }

    @Transactional(readOnly = true, timeout = 10)
    public Page<SessionResponseDTO> findSessionByName(String name, Pageable pageable) {
        if (sessionValidation.validateFields(name))
            throw new ResourceNotFoundException("This Resource does not exit");

        return sessionMapper.sessionToListDTO(sessionRepository.findByPatient_NameContainingIgnoreCase(name, pageable));
    }

    @Transactional(readOnly = true, timeout = 10)
    public Page<SessionResponseDTO> findByStartDate(LocalDate start, LocalDate end, Pageable pageable) {
        if (sessionValidation.validateFields(start, end))
            throw new ResourceNotFoundException("This Resource does not exit");

        return sessionMapper.sessionToListDTO(sessionRepository.findByStartDateBetween(start, end, pageable));
    }

}
