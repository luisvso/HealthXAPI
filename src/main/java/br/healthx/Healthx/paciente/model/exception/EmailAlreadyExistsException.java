package br.healthx.Healthx.paciente.model.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("The email: " + email + " is already registered for another patient");
    }
}
