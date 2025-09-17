package br.healthx.Healthx.paciente.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.healthx.Healthx.paciente.dto.PacienteDTO;
import br.healthx.Healthx.paciente.model.entity.Paciente;
import br.healthx.Healthx.paciente.model.repository.PacienteRepository;
import br.healthx.Healthx.paciente.model.validation.PacienteValidation;

@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;
    private PacienteValidation pacienteValidation;

    public PacienteService(PacienteRepository pacienteRepository, PacienteValidation pacienteValidation) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteValidation = pacienteValidation;
    }

    public List<Paciente> createListPaciente(List<PacienteDTO> dtos) {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        for (PacienteDTO dto : dtos)
            pacientes.add(create(dto));
        return pacientes;
    }

    /// method for creating Paciente
    public Paciente create(PacienteDTO dto) {
        return pacienteRepository.save(new Paciente(dto));
    }

    public String delete(PacienteDTO dto) {
        if (pacienteValidation.existId(dto))
            pacienteRepository.delete(new Paciente(dto));
            return "User deletado";
    }

    public Paciente update(PacienteDTO dto) {
        return (pacienteValidation.existId(dto) ? create(dto) : null);
    }

    public Paciente find(PacienteDTO dto) {
        return (pacienteValidation.existId(dto) ? pacienteRepository.findById(dto.id()).get() : null);
    }

    // public Paciente findName(PacienteDTO dto) {
    // }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
}
