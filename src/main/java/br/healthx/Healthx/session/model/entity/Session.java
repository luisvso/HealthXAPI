package br.healthx.Healthx.session.model.entity;

import java.time.LocalDate;

import br.healthx.Healthx.paciente.model.entity.Patient;
import br.healthx.Healthx.psychologist.model.entity.Psychologist;
import br.healthx.Healthx.session.dto.SessionRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Session {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull(message = "The startDate must not be null")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "The patient must not be null")
    @JoinColumn(name = "paciente_id")
    @ManyToOne
    private Patient patient;

    private String notes;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "The session status should not be null")
    private Status status;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "The session SessionType should not be null")
    private SessionType sessionType;

    @NotNull(message = "The session complaint should not be null")
    private String sessionComplaint;

    @ManyToOne
    @NotNull(message = "The field psychologist must not be blank")
    @JoinColumn(name = "psychologist_id")
    private Psychologist psychologist;

    public Session() {
    }

    public Session(SessionRequestDTO dto) {
        this.startDate = dto.startDate();
        this.endDate = dto.endDate();
        this.patient = dto.patient();
        this.notes = dto.notes();
        this.status = dto.status();
        this.sessionType = dto.sessionType();
        this.sessionComplaint = dto.sessionComplaint();
        this.psychologist = dto.psychologist();
    }

}
