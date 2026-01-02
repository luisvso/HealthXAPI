package br.healthx.Healthx.psychologist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import br.healthx.Healthx.psychologist.dto.PsychologistRequestDTO;
import br.healthx.Healthx.psychologist.model.service.PsychologistService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("api/psychologist")
public class PsychologistController {

    private final PsychologistService psychologistService;

    public PsychologistController(PsychologistService psychologistService) {
        this.psychologistService = psychologistService;
    }

    @PostMapping
    public ResponseEntity<PsychologistRequestDTO> create(@RequestBody @Valid PsychologistRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(psychologistService.create(dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        psychologistService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsychologistRequestDTO> update(@PathVariable Long id,
            @RequestBody @Valid PsychologistRequestDTO dto) {
        return ResponseEntity.ok(psychologistService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsychologistRequestDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(psychologistService.findOne(id));
    }

    @GetMapping
    public ResponseEntity<List<PsychologistRequestDTO>> findAll() {
        return ResponseEntity.ok(psychologistService.listAll());
    }

}
