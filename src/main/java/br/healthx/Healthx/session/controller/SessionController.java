package br.healthx.Healthx.session.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.healthx.Healthx.session.dto.SessionRequestDTO;
import br.healthx.Healthx.session.dto.SessionResponseDTO;
import br.healthx.Healthx.session.model.service.SessionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/session")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<SessionResponseDTO> create(@RequestBody @Valid SessionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.createSession(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> update(@PathVariable Long id, @RequestBody @Valid SessionRequestDTO dto) {
        return ResponseEntity.ok(sessionService.updateSession(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sessionService.deleteSession(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.findSession(id));
    }

    @GetMapping
    public ResponseEntity<List<SessionResponseDTO>> findAll() {
        return ResponseEntity.ok(sessionService.findAllSessions());
    }

}
