package br.healthx.Healthx.paciente.model.exception;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(Long id) {
        super("The patient of id " + id + " does not exits");
    }
}
