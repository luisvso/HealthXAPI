package br.healthx.Healthx.paciente.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.healthx.Healthx.paciente.dto.RequestPacienteDTO;
import br.healthx.Healthx.paciente.dto.ResponsePacienteDTO;
import br.healthx.Healthx.paciente.model.entity.Genero;
import br.healthx.Healthx.paciente.model.entity.Paciente;
import br.healthx.Healthx.paciente.model.entity.Raca;

@Component
public class PacienteMapper {

    public Paciente toEntity(RequestPacienteDTO dto) {

        Paciente paciente = new Paciente();

        paciente.setEmail(dto.email());
        paciente.setDataNascimento(dto.dataNascimento());
        paciente.setName(dto.name());
        paciente.setGenero(dto.genero());
        paciente.setTelefone(dto.telefone());
        paciente.setResponsavel(dto.responsavel());
        paciente.setRaca(dto.raca());
        paciente.setCriadoEm(LocalDate.now());

        return paciente;
    }

    public ResponsePacienteDTO entityToResponse(Paciente paciente) {
        String email = paciente.getEmail();
        LocalDate dataNascimento = paciente.getDataNascimento();
        String name = paciente.getName();
        Genero genero = paciente.getGenero();
        String telefone = paciente.getTelefone();
        String responsavel = paciente.getResponsavel();
        Raca raca = paciente.getRaca();
        LocalDate criadoEm = paciente.getCriadoEm();

        ResponsePacienteDTO dto = new ResponsePacienteDTO(name, dataNascimento, genero, responsavel, raca, email,
                telefone, criadoEm);

        return dto;
    }

    public List<ResponsePacienteDTO> toResponseList(List<Paciente> pacientes) {
        List<ResponsePacienteDTO> responseList = pacientes.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());

        return responseList;
    }

}
