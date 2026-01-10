package br.healthx.Healthx.session.dto;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.model.entity.Paciente;
import br.healthx.Healthx.psychologist.model.entity.Psychologist;
import br.healthx.Healthx.session.model.entity.SessionType;
import br.healthx.Healthx.session.model.entity.Status;
import jakarta.validation.constraints.NotNull;

public record SessionResponseDTO(Long id, LocalDate startDate,
        LocalDate endDate,
        Paciente patient, String notes, Status status,
        SessionType sessionType, String sessionComplaint,
        Psychologist psychologist) {
}
