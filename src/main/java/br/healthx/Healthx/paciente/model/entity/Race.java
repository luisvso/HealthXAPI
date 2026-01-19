package br.healthx.Healthx.paciente.model.entity;

public enum Race {
    WHITE('W'),
    BLACK('B'),
    OTHER('O');

    private char raceCode;

    Race(char raceCode) {
        this.raceCode = raceCode;
    }
}
