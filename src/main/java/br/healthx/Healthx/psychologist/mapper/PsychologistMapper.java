package br.healthx.Healthx.psychologist.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.healthx.Healthx.psychologist.dto.PsychologistRequestDTO;
import br.healthx.Healthx.psychologist.model.entity.Psychologist;

@Component
public class PsychologistMapper {

    public PsychologistRequestDTO psychologistToDTO(Psychologist psychologist) {
        Psychologist psychologist2 = new Psychologist();
        psychologist2.setEmail(psychologist.getEmail());
        psychologist2.setCRP(psychologist.getCRP());
        psychologist2.setName(psychologist.getName());
        psychologist2.setPhone(psychologist.getPhone());
        psychologist2.setLogin(psychologist.getLogin());
        psychologist2.setPassword(psychologist.getPassword());
        psychologist2.setRole(psychologist.getRole());

        return new PsychologistRequestDTO(psychologist2.getEmail(), psychologist2.getPhone(), psychologist2.getName(),
                psychologist2.getCRP(), psychologist2.getLogin(), psychologist2.getPassword(), psychologist2.getRole());
    }

    public Psychologist dtoToPsychologist(PsychologistRequestDTO dto) {
        Psychologist psychologist = new Psychologist();
        psychologist.setCRP(dto.CRP());
        psychologist.setEmail(dto.email());
        psychologist.setName(dto.name());
        psychologist.setPhone(dto.phone());
        psychologist.setLogin(dto.login());
        psychologist.setPassword(dto.password());
        psychologist.setRole(dto.role());

        return psychologist;
    }

    public Page<PsychologistRequestDTO> listToPsychologistDTO(Page<Psychologist> psychologists) {
        Page<PsychologistRequestDTO> psychologistList = psychologists.map(this::psychologistToDTO);

        return psychologistList;
    }

}
