package br.healthx.Healthx.psychologist.mapper;

import java.util.List;
import java.util.stream.Collectors;

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

        return new PsychologistRequestDTO(psychologist2.getEmail(), psychologist2.getPhone(), psychologist2.getName(),
                psychologist2.getCRP());
    }

    public Psychologist dtoToPsychologist(PsychologistRequestDTO dto) {
        Psychologist psychologist = new Psychologist();
        psychologist.setCRP(dto.CRP());
        psychologist.setEmail(dto.email());
        psychologist.setName(dto.name());
        psychologist.setPhone(dto.phone());

        return psychologist;
    }

    public Page<PsychologistRequestDTO> listToPsychologistDTO(Page<Psychologist> psychologists) {
        Page<PsychologistRequestDTO> psychologistList = psychologists.map(this::psychologistToDTO);

        return psychologistList;
    }

}
