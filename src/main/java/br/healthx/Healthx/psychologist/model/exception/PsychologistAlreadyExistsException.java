package br.healthx.Healthx.psychologist.model.exception;

public class PsychologistAlreadyExistsException extends RuntimeException {
    public PsychologistAlreadyExistsException(String psychologist) {
        super(psychologist);
    }

}
