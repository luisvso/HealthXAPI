package br.healthx.Healthx.paciente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;

import br.healthx.Healthx.paciente.dto.RequestPacienteDTO;
import br.healthx.Healthx.paciente.dto.ResponsePacienteDTO;
import br.healthx.Healthx.paciente.model.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<ResponsePacienteDTO> add(@RequestBody @Valid RequestPacienteDTO dto) {
        ResponsePacienteDTO responsePacienteDTO = pacienteService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePacienteDTO);
    }

    // @PostMapping("/addAll")
    // public ResponseEntity<List<Paciente>> addAll(@RequestBody @Valid
    // List<PacienteDTO> dtos) {
    // return ResponseEntity.ok(pacienteService.createListPaciente(dtos));
    // }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pacienteService.delete(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePacienteDTO> findOne(@PathVariable Long id) {
        ResponsePacienteDTO responsePacienteDTO = pacienteService.findById(id);
        return ResponseEntity.ok(responsePacienteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePacienteDTO> update(@RequestBody @Valid RequestPacienteDTO dto,
            @PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponsePacienteDTO>> findAll() {
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ResponsePacienteDTO>> findName(@PathVariable String name) {
        return ResponseEntity.ok(pacienteService.findByName(name));
    }

}
