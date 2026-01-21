package br.healthx.Healthx.psychologist.controller;

import br.healthx.Healthx.User.User;
import br.healthx.Healthx.psychologist.mapper.PsychologistMapper;
import br.healthx.Healthx.psychologist.model.entity.Psychologist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.healthx.Healthx.psychologist.dto.PsychologistRequestDTO;
import br.healthx.Healthx.psychologist.model.service.PsychologistService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("api/psychologist")
public class PsychologistController {

    private final PsychologistService psychologistService;
    private final PsychologistMapper psychologistMapper;

    public PsychologistController(PsychologistService psychologistService, PsychologistMapper psychologistMapper) {
        this.psychologistService = psychologistService;
        this.psychologistMapper = psychologistMapper;
    }

    @PostMapping
    public ResponseEntity<PsychologistRequestDTO> create(@RequestBody @Valid PsychologistRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(psychologistService.create(dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        psychologistService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsychologistRequestDTO> update(@PathVariable("id") Long id,
            @RequestBody @Valid PsychologistRequestDTO dto) {
        return ResponseEntity.ok(psychologistService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsychologistRequestDTO> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(psychologistService.findOne(id));
    }

    @GetMapping
    public ResponseEntity<Page<PsychologistRequestDTO>> findAll(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(psychologistService.listAll(pageable));
    }

    @GetMapping("/me")
    public ResponseEntity<PsychologistRequestDTO> getMyProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Psychologist psychologist = psychologistService.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Psychologist not found"));

        return ResponseEntity.ok(psychologistMapper.psychologistToDTO(psychologist));
    }


}
