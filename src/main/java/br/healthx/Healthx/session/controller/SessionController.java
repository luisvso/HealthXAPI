package br.healthx.Healthx.session.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import br.healthx.Healthx.session.model.entity.SessionType;
import br.healthx.Healthx.session.model.entity.Status;
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
    public ResponseEntity<SessionResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody @Valid SessionRequestDTO dto) {
        return ResponseEntity.ok(sessionService.updateSession(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        sessionService.deleteSession(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sessionService.findSession(id));
    }

    @GetMapping
    public ResponseEntity<Page<SessionResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        // Sort sort = ascending ? Sort.by(sortBy).ascending() :
        // Sort.by(sortBy).descending();
        // Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(sessionService.findAllSessions(pageable));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<SessionResponseDTO>> findByStatus(@PathVariable("status") Status status,
            @PageableDefault(size = 10, sort = "Status", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(sessionService.findByStatus(status, pageable));
    }

    @GetMapping("/sessionType/{session_type}")
    public ResponseEntity<Page<SessionResponseDTO>> findBySessionType(
            @PathVariable("session_type") SessionType sessionType,
            @PageableDefault(size = 10, sort = "sessionType", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(sessionService.findBySessionType(sessionType, pageable));

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<SessionResponseDTO>> findSessionByPatientName(@PathVariable("name") String name,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(sessionService.findSessionByName(name, pageable));

    }

    @GetMapping("/start/{start}/end/{end}")
    public ResponseEntity<Page<SessionResponseDTO>> findByStartDate(@PathVariable("start") LocalDate start,
            @PathVariable("end") LocalDate end,
            @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(sessionService.findByStartDate(start, end, pageable));
    }

}
