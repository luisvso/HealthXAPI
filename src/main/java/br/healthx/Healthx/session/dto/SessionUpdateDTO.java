package br.healthx.Healthx.session.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.healthx.Healthx.paciente.model.entity.Patient;
import br.healthx.Healthx.psychologist.model.entity.Psychologist;
import br.healthx.Healthx.session.model.entity.SessionType;
import br.healthx.Healthx.session.model.entity.Status;
import jakarta.validation.constraints.NotNull;

public record SessionUpdateDTO(@NotNull(message = "The startDate must not be null") LocalDate date,
        @NotNull(message = "The patient must not be null") Patient patient, String notes,
        @NotNull(message = "The session status should not be null") Status status,
        @NotNull(message = "The sessionType should not be null") SessionType sessionType,
        @NotNull(message = "The session complaint should not be null") String sessionComplaint,
        @NotNull(message = "The field psychologist must not be blank") Psychologist psychologist,
        @NotNull(message = "The startTime should not be null") LocalTime startTime,
        @NotNull(message = "The endTime should not be null") LocalTime endTime) {
}
