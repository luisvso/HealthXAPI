package br.healthx.Healthx.paciente.model.exception;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(String message) {
        super(message);
    }
}
