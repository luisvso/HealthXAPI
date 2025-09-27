package br.healthx.Healthx.paciente.model.entity;

import jakarta.validation.constraints.NotNull;

public class PacienteClinico {

    @NotNull
    private String queixaInicial;

    @NotNull
    private String observacoes;
}
