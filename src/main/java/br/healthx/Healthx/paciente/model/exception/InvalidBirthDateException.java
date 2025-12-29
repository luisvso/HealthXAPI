package br.healthx.Healthx.paciente.model.exception;

import java.time.LocalDate;

public class InvalidBirthDateException extends RuntimeException {
    public InvalidBirthDateException(LocalDate date) {
        super("The birth date " + date + " is Invalid");
    }
}
