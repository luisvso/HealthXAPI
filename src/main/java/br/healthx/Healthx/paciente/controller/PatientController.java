package br.healthx.Healthx.paciente.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import br.healthx.Healthx.paciente.dto.RequestPatientDTO;
import br.healthx.Healthx.paciente.dto.ResponsePatientDTO;
import br.healthx.Healthx.paciente.model.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Patient", description = "This is the endpoint for patients managment")
@RequestMapping("/paciente")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @Operation(summary = "Create a new Patient", description = "This methods creates a new Patient and register on the database")
    public ResponseEntity<ResponsePatientDTO> add(@RequestBody @Valid RequestPatientDTO dto) {
        ResponsePatientDTO responsePacienteDTO = patientService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePacienteDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient", description = "Delete a patient that is previously registered on the system database")
    public void delete(@PathVariable("id") Long id) {
        patientService.delete(id);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Find one Patient", description = "Finds one Patient using their Id")
    public ResponseEntity<ResponsePatientDTO> findOne(@PathVariable("id") Long id) {
        ResponsePatientDTO responsePacienteDTO = patientService.findById(id);
        return ResponseEntity.ok(responsePacienteDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Upadte a Patient", description = "Upadtes a Patient that is registered previously on the system")
    public ResponseEntity<ResponsePatientDTO> update(@RequestBody @Valid RequestPatientDTO dto,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(patientService.update(id, dto));
    }

    @GetMapping
    @Operation(summary = "Find all Patients", description = "Find all the patients registered on the system")
    public ResponseEntity<Page<ResponsePatientDTO>> findAll(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(patientService.findAll(pageable));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Find a patient by their name", description = "Find a Patient passing their name as a parameter")
    public ResponseEntity<Page<ResponsePatientDTO>> findName(@PathVariable("name") String name,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(patientService.findByName(name, pageable));
    }

}
