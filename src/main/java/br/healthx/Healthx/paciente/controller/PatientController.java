package br.healthx.Healthx.paciente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;

import br.healthx.Healthx.paciente.dto.RequestPatientDTO;
import br.healthx.Healthx.paciente.dto.ResponsePatientDTO;
import br.healthx.Healthx.paciente.model.service.PatientService;

@RestController
@RequestMapping("/paciente")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<ResponsePatientDTO> add(@RequestBody @Valid RequestPatientDTO dto) {
        ResponsePatientDTO responsePacienteDTO = patientService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePacienteDTO);
    }

    // @PostMapping("/addAll")
    // public ResponseEntity<List<Paciente>> addAll(@RequestBody @Valid
    // List<PacienteDTO> dtos) {
    // return ResponseEntity.ok(pacienteService.createListPaciente(dtos));
    // }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        patientService.delete(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePatientDTO> findOne(@PathVariable("id") Long id) {
        ResponsePatientDTO responsePacienteDTO = patientService.findById(id);
        return ResponseEntity.ok(responsePacienteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePatientDTO> update(@RequestBody @Valid RequestPatientDTO dto,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(patientService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponsePatientDTO>> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ResponsePatientDTO>> findName(@PathVariable("name") String name) {
        return ResponseEntity.ok(patientService.findByName(name));
    }

}
