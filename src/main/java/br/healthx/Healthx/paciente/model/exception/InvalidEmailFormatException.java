package br.healthx.Healthx.paciente.model.exception;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException(String email) {
        super("The email " + email + " is not in a valid format");
    }
}
