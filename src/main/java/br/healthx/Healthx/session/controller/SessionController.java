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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Session", description = "This is the session endpoints for managment of sessions")
@RequestMapping("api/session")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    @Operation(summary = "Create a Session", description = "Creates a session one at a time, considering time, date and patient conflicts")
    public ResponseEntity<SessionResponseDTO> create(@RequestBody @Valid SessionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.createSession(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a session", description = "Updates a session that it's created on the database passing their Id as parameter, and considering time, date and patient conflict")
    public ResponseEntity<SessionResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody @Valid SessionRequestDTO dto) {
        return ResponseEntity.ok(sessionService.updateSession(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a session", description = "Deletes a session that is created on the database passing their Id")
    public void delete(@PathVariable("id") Long id) {
        sessionService.deleteSession(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find one session", description = "Find one Session that is registered on the database using their Id")
    public ResponseEntity<SessionResponseDTO> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sessionService.findSession(id));
    }

    @GetMapping
    @Operation(summary = "Find all sessions", description = "Find all sessions that have been registered on the database")
    public ResponseEntity<Page<SessionResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(sessionService.findAllSessions(pageable));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Find a session by status", description = "Find a session using their status passing ")
    public ResponseEntity<Page<SessionResponseDTO>> findByStatus(@PathVariable("status") Status status,
            @PageableDefault(size = 10, sort = "Status", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(sessionService.findByStatus(status, pageable));
    }

    @GetMapping("/sessionType/{session_type}")
    @Operation(summary = "Find a session by Session Type", description = "Find a session using their session Type as parameter")
    public ResponseEntity<Page<SessionResponseDTO>> findBySessionType(
            @PathVariable("session_type") SessionType sessionType,
            @PageableDefault(size = 10, sort = "sessionType", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(sessionService.findBySessionType(sessionType, pageable));

    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Find a session by Patient name", description = "Find a session using a patient name that is registered on the patient database as parameter to find a session that has been registered")
    public ResponseEntity<Page<SessionResponseDTO>> findSessionByPatientName(@PathVariable("name") String name,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(sessionService.findSessionByName(name, pageable));

    }

    @GetMapping("/start/{start}/end/{end}")
    @Operation(summary = "Find a session by date", description = "Allow to find a session that is between a StartDate and EndDate")
    public ResponseEntity<Page<SessionResponseDTO>> findByStartDate(@PathVariable("start") LocalDate start,
            @PathVariable("end") LocalDate end,
            @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(sessionService.findByStartDate(start, end, pageable));
    }

}
