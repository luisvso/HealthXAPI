package br.healthx.Healthx.paciente.dto;

import java.time.LocalDate;
import br.healthx.Healthx.paciente.model.entity.Genero;
import br.healthx.Healthx.paciente.model.entity.Raca;

public record ResponsePacienteDTO(
        String name,
        LocalDate dataNascimento,
        Genero genero,
        String responsavel,
        Raca raca,
        String email,
        String telefone,
        LocalDate criadoEm) {
}
