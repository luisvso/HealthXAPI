package br.healthx.Healthx.paciente.dto;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.model.entity.Genero;

public record PacienteDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String email,
        String telefone,
        String queixaInicial,
        String observacoes,
        Genero genero,
        String responsavel,
        String raca) {
}
