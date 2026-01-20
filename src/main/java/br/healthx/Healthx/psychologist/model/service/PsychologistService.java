package br.healthx.Healthx.psychologist.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.healthx.Healthx.psychologist.dto.PsychologistRequestDTO;
import br.healthx.Healthx.psychologist.mapper.PsychologistMapper;
import br.healthx.Healthx.psychologist.model.entity.Psychologist;
import br.healthx.Healthx.psychologist.model.repository.PsychologistRepository;
import br.healthx.Healthx.psychologist.model.validation.PsychologistValidation;

/**
 *
 * @author Luis Valdo
 *
 * @email luisvso.dev@gmail.com
 *
 *
 */
@Service
public class PsychologistService {

    private final PsychologistRepository psychologistRepository;
    private final PsychologistMapper psychologistMapper;
    private final PsychologistValidation psychologistValidation;

    public PsychologistService(PsychologistRepository psychologistRepository, PsychologistMapper psychologistMapper,
            PsychologistValidation psychologistValidation) {
        this.psychologistRepository = psychologistRepository;
        this.psychologistMapper = psychologistMapper;
        this.psychologistValidation = psychologistValidation;
    }

    @Transactional(timeout = 30)
    public PsychologistRequestDTO create(PsychologistRequestDTO dto) {
        psychologistValidation.validatePsychologist(dto);

        Psychologist psy = psychologistMapper.dtoToPsychologist(dto);

        psychologistRepository.save(psy);

        return psychologistMapper.psychologistToDTO(psy);
    }

    @Transactional(timeout = 30)
    public PsychologistRequestDTO update(Long id, PsychologistRequestDTO dto) {
        psychologistValidation.validatePsychologist(dto);
        Psychologist psy = psychologistRepository.findById(id).get();

        psy.setCRP(dto.CRP());
        psy.setEmail(dto.email());
        psy.setName(dto.name());
        psy.setPhone(dto.phone());

        psy = psychologistRepository.save(psy);

        return psychologistMapper.psychologistToDTO(psy);
    }

    @Transactional(timeout = 30)
    public void delete(Long id) {
        psychologistRepository.deleteById(id);
    }

    @Transactional(readOnly = true, timeout = 15)
    public PsychologistRequestDTO findOne(Long id) {
        Psychologist psychologist = psychologistRepository.findById(id).get();
        return psychologistMapper.psychologistToDTO(psychologist);
    }

    @Transactional(readOnly = true, timeout = 15)
    public List<PsychologistRequestDTO> listAll() {
        return psychologistMapper.listToPsychologistDTO(psychologistRepository.findAll());
    }

}
