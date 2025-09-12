package br.healthx.Healthx.paciente.dto;

import java.time.LocalDate;

import io.micrometer.common.lang.NonNull;

public record PacienteDTO(
        Long id,
        @NonNull String nome,
        @NonNull LocalDate dataNascimento,
        @NonNull String email,
        @NonNull String telefone,
        @NonNull String motivo,
        String observacoes) {
}
