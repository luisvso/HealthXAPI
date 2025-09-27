package br.healthx.Healthx.paciente.dto;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.model.entity.Genero;
import br.healthx.Healthx.paciente.model.entity.PacienteClinico;
import br.healthx.Healthx.paciente.model.entity.PacienteContato;

public record PacienteDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        Genero genero,
        String responsavel,
        String raca,
        PacienteContato pacienteContato,
        PacienteClinico pacienteClinico) {
}
