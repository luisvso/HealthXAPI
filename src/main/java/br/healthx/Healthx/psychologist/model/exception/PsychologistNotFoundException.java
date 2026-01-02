package br.healthx.Healthx.psychologist.model.exception;

public class PsychologistNotFoundException extends RuntimeException {
    public PsychologistNotFoundException(String psychologist) {
        super(psychologist);
    }
}
