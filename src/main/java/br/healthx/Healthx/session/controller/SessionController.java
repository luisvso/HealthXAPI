package br.healthx.Healthx.session.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.healthx.Healthx.session.dto.SessionRequestDTO;
import br.healthx.Healthx.session.dto.SessionResponseDTO;
import br.healthx.Healthx.session.model.service.SessionService;
import br.healthx.Healthx.session.model.entity.SessionType;
import br.healthx.Healthx.session.model.entity.Status;
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
    public ResponseEntity<Page<SessionResponseDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "ascending", defaultValue = "true") boolean ascending) {

        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(sessionService.findAllSessions(pageable));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<SessionResponseDTO>> findByStatus(@PathVariable("status") Status status,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "status") String sortBy,
            @RequestParam(name = "ascending", defaultValue = "true") boolean ascending) {

        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(sessionService.findByStatus(status, pageable));
    }

    @GetMapping("/sessionType/{session_type}")
    public ResponseEntity<Page<SessionResponseDTO>> findBySessionType(
            @PathVariable("session_type") SessionType sessionType,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "sessionType") String sortBy,
            @RequestParam(name = "ascending", defaultValue = "true") boolean ascending) {

        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(sessionService.findBySessionType(sessionType, pageable));

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<SessionResponseDTO>> findSessionByPatientName(@PathVariable("name") String name,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "ascending", defaultValue = "true") boolean ascending) {

        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(sessionService.findSessionByName(name, pageable));

    }

    @GetMapping("/start/{start}/end/{end}")
    public ResponseEntity<Page<SessionResponseDTO>> findByStartDate(@PathVariable("start") LocalDate start,
            @PathVariable("end") LocalDate end, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "startDate") String sortBy,
            @RequestParam(name = "ascending", defaultValue = "true") boolean ascending) {

        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(sessionService.findByStartDate(start, end, pageable));
    }

}
