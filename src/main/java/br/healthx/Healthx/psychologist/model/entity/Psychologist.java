package br.healthx.Healthx.psychologist.model.entity;

import br.healthx.Healthx.psychologist.dto.PsychologistRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Psychologist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Your Email must not be blank")
    private String email;

    @NotBlank(message = "The Phone must not be null")
    private String phone;

    @NotBlank(message = "The name must not be blank")
    private String name;

    @NotBlank(message = "The CRP must not be blank")
    private String CRP;

    public Psychologist() {
    }

    public Psychologist(PsychologistRequestDTO dto) {
        this.email = dto.email();
        this.phone = dto.phone();
        this.name = dto.name();
        this.CRP = dto.CRP();
    }

}
