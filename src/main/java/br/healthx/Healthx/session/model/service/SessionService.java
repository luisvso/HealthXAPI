package br.healthx.Healthx.session.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.healthx.Healthx.session.dto.SessionRequestDTO;
import br.healthx.Healthx.session.dto.SessionResponseDTO;
import br.healthx.Healthx.session.mapper.SessionMapper;
import br.healthx.Healthx.session.model.entity.Session;
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

    public SessionResponseDTO createSession(SessionRequestDTO dto) {

        sessionValidation.validateSessionCreation(dto);

        Session session = sessionMapper.DtoToSession(dto);

        sessionRepository.save(session);

        return sessionMapper.sessionToDTO(session);
    }

    public SessionResponseDTO updateSession(Long id, SessionRequestDTO dto) {
        sessionValidation.validateSessionCreation(dto);
        Session session = sessionRepository.findById(id).get();

        return sessionMapper.sessionToDTO(sessionMapper.mapperUpateSession(session, dto));
    }

    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }

    public SessionResponseDTO findSession(Long id) {
        Session session = sessionRepository.findById(id).get();

        return sessionMapper.sessionToDTO(session);
    }

    public List<SessionResponseDTO> findAllSessions() {
        return sessionMapper.sessionToListDTO(sessionRepository.findAll());
    }

}
