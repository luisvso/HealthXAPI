package br.healthx.Healthx.paciente.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;

import br.healthx.Healthx.paciente.dto.PacienteDTO;
import br.healthx.Healthx.paciente.model.entity.Paciente;
import br.healthx.Healthx.paciente.model.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/add")
    public Paciente add(@RequestBody @Valid PacienteDTO dto) {
        return pacienteService.create(dto);
    }

    @PostMapping("/delete")
    public String delete(@RequestBody @Valid PacienteDTO dto) {
        return pacienteService.delete(dto);
        
    }

    @GetMapping("/one")
    public Paciente findOne(@RequestBody @Valid PacienteDTO dto) {
        return pacienteService.find(dto);
    }

    @PostMapping("/update")
    public Paciente update(@RequestBody @Valid PacienteDTO dto) {
        return pacienteService.update(dto);
    }

    @GetMapping("/findall")
    public List<Paciente> findAll() {
        return pacienteService.findAll();
    }

    @GetMapping("/findNome")
    public List<Paciente> findNome(@RequestBody @Valid String name){
        return pacienteService.findByNome(name);
    }

}
