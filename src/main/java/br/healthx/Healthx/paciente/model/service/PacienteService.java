package br.healthx.Healthx.paciente.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sun.net.httpserver.Request;

import br.healthx.Healthx.paciente.dto.RequestPacienteDTO;
import br.healthx.Healthx.paciente.dto.ResponsePacienteDTO;
import br.healthx.Healthx.paciente.mapper.PacienteMapper;
import br.healthx.Healthx.paciente.model.entity.Paciente;
import br.healthx.Healthx.paciente.model.repository.PacienteRepository;
import br.healthx.Healthx.paciente.model.validation.PacienteValidation;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteValidation pacienteValidation;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteValidation pacienteValidation,
            PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteValidation = pacienteValidation;
        this.pacienteMapper = pacienteMapper;
    }

    // public List<Paciente> createListPaciente(List<RequestPacienteDTO> dtos) {
    // List<Paciente> pacientes = new ArrayList<Paciente>();
    // for (RequestPacienteDTO dto : dtos)
    // pacientes.add(create(dto));
    // return pacientes;
    // }

    /// method for creating Paciente
    public ResponsePacienteDTO create(RequestPacienteDTO dto) {
        Paciente paciente = pacienteMapper.toEntity(dto);
        paciente = pacienteRepository.save(paciente);

        return pacienteMapper.entityToResponse(paciente);

    }

    public void delete(Long id) {
        pacienteValidation.existId(id);
        pacienteRepository.deleteById(id);
    }

    public ResponsePacienteDTO update(Long id, RequestPacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(id).get();

        paciente.setName(dto.name());
        paciente.setEmail(dto.email());
        paciente.setDataNascimento(dto.dataNascimento());
        paciente.setGenero(dto.genero());
        paciente.setTelefone(dto.telefone());
        paciente.setResponsavel(dto.responsavel());
        paciente.setRaca(dto.raca());

        paciente = pacienteRepository.save(paciente);

        return pacienteMapper.entityToResponse(paciente);

    }

    public ResponsePacienteDTO findById(Long id) {
        pacienteValidation.existId(id);
        return pacienteMapper.entityToResponse(pacienteRepository.findById(id).get());
    }

    public List<ResponsePacienteDTO> findByName(String name) {
        return pacienteMapper.toResponseList(pacienteRepository.findByNameContainingIgnoreCase(name));
    }

    public List<ResponsePacienteDTO> findAll() {
        return pacienteMapper.toResponseList(pacienteRepository.findAll());
    }
}
