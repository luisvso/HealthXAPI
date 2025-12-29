package br.healthx.Healthx.paciente.model.exception;

public class PacienteNaoExisteException extends RuntimeException {
    public PacienteNaoExisteException(String message) {
        super(message);
    }
}
