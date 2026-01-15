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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

        log.info("Attempt to create an session");

        sessionValidation.validateSession(dto);

        Session session = sessionMapper.DtoToSession(dto);

        sessionRepository.save(session);

        log.info("Session with ID: {} created sucessfully", session.getId());

        return sessionMapper.sessionToDTO(session);
    }

    @Transactional(timeout = 10)
    public SessionResponseDTO updateSession(Long id, SessionRequestDTO dto) {
        sessionValidation.validateSession(dto);
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        log.info("Trying to update a session of id: {}", id);

        session = sessionRepository.save(sessionMapper.mapperUpdateSession(session, dto));

        log.info("Session of id: {} updated sucessfully", id);

        return sessionMapper.sessionToDTO(session);
    }

    @Transactional(timeout = 10)
    public void deleteSession(Long id) throws NoSuchElementException {
        if (sessionValidation.ExistSessionId(id)) {
            throw new NoSuchElementException("The Session of id : " + id + " does not exist");
        }

        log.info("Trying to delete session of ID: {} ", id);

        sessionRepository.deleteById(id);

        log.info("Session of ID: {} deleted sucessfully", id);

    }

    @Transactional(readOnly = true, timeout = 10)
    public SessionResponseDTO findSession(Long id) {
        if (sessionValidation.validateFields(id))
            throw new ResourceNotFoundException("This Resource does not exit");

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        log.info("Trying to fetch a session with the ID: {} ", id);

        return sessionMapper.sessionToDTO(session);
    }

    @Transactional(readOnly = true, timeout = 10)
    public Page<SessionResponseDTO> findAllSessions(Pageable pageable) {

        log.info("Trying to fetch all the sessions created");

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

        log.info("Trying to fetch A session by status {} ", status);
        return sessionMapper.sessionToListDTO(sessionRepository.findByStatus(status, pageable));
    }

    @Transactional(readOnly = true, timeout = 10)
    public Page<SessionResponseDTO> findBySessionType(SessionType sessionType, Pageable pageable) {
        if (sessionValidation.validateFields(sessionType))
            throw new ResourceNotFoundException("This Resource does not exit");

        log.info("Trying to fetch a session containing the sessionType: {}", sessionType);
        return sessionMapper.sessionToListDTO(sessionRepository.findBySessionType(sessionType, pageable));
    }

    @Transactional(readOnly = true, timeout = 10)
    public Page<SessionResponseDTO> findSessionByName(String name, Pageable pageable) {
        if (sessionValidation.validateFields(name))
            throw new ResourceNotFoundException("This Resource does not exit");

        log.info("Trying to fetch a session that contain the patient name: {} ", name);
        return sessionMapper.sessionToListDTO(sessionRepository.findByPatient_NameContainingIgnoreCase(name, pageable));
    }

    @Transactional(readOnly = true, timeout = 10)
    public Page<SessionResponseDTO> findByStartDate(LocalDate start, LocalDate end, Pageable pageable) {
        if (sessionValidation.validateFields(start, end))
            throw new ResourceNotFoundException("This Resource does not exit");

        log.info("Trying to fetch Session that is between the startDate: {} and the endDate {}", start, end);
        return sessionMapper.sessionToListDTO(sessionRepository.findByStartDateBetween(start, end, pageable));
    }

}
