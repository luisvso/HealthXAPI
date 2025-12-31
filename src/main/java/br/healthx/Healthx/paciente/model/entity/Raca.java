package br.healthx.Healthx.paciente.model.entity;

public enum Raca {
    BRANCO('B'),
    NEGRO('N'),
    OUTRO('O');

    private char valorRaca;

    Raca(char valorRaca) {
        this.valorRaca = valorRaca;
    }
}
