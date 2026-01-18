package br.healthx.Healthx.session.dto;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.model.entity.Patient;
import br.healthx.Healthx.psychologist.model.entity.Psychologist;
import br.healthx.Healthx.session.model.entity.SessionType;
import br.healthx.Healthx.session.model.entity.Status;

public record SessionRequestDTO(LocalDate startDate,
        LocalDate endDate,
        Patient patient, String notes, Status status,
        SessionType sessionType, String sessionComplaint,
        Psychologist psychologist) {
}
