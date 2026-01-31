package br.healthx.Healthx.psychologist.model.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.healthx.Healthx.User.User;
import br.healthx.Healthx.User.UserService;
import br.healthx.Healthx.psychologist.dto.PsychologistRequestDTO;
import br.healthx.Healthx.psychologist.mapper.PsychologistMapper;
import br.healthx.Healthx.psychologist.model.entity.Psychologist;
import br.healthx.Healthx.psychologist.model.repository.PsychologistRepository;
import br.healthx.Healthx.psychologist.model.validation.PsychologistValidation;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Luis Valdo
 *
 * @email luisvso.dev@gmail.com
 *
 *
 */
@Service
@Slf4j
public class PsychologistService {

    private final PsychologistRepository psychologistRepository;
    private final PsychologistMapper psychologistMapper;
    private final PsychologistValidation psychologistValidation;

    private final UserService userService;

    public PsychologistService(PsychologistRepository psychologistRepository, PsychologistMapper psychologistMapper,
            PsychologistValidation psychologistValidation, UserService userService) {
        this.psychologistRepository = psychologistRepository;
        this.psychologistMapper = psychologistMapper;
        this.psychologistValidation = psychologistValidation;
        this.userService = userService;
    }

    @Transactional(timeout = 30)
    public PsychologistRequestDTO create(PsychologistRequestDTO dto) {
        log.info("Trying to initialize the method to create a Psychologist");

        psychologistValidation.validatePsychologist(dto);

        Psychologist psy = psychologistMapper.dtoToPsychologist(dto);

        User user = userService.creteUser(dto);
        psy.setLogin(user.getLogin());
        psy.setPassword(user.getPassword());
        psy.setRole(user.getRole());

        psychologistRepository.save(psy);

        return psychologistMapper.psychologistToDTO(psy);
    }

    @Transactional(timeout = 30)
    public PsychologistRequestDTO update(Long id, PsychologistRequestDTO dto) {

        log.info("The method that updates the psychologist has started");

        psychologistValidation.validatePsychologist(dto);
        Psychologist psy = psychologistRepository.findById(id).get();

        psy.setCRP(dto.CRP());
        psy.setEmail(dto.email());
        psy.setName(dto.name());
        psy.setPhone(dto.phone());
        psy.setLogin(dto.login());
        psy.setPassword(dto.password());
        psy.setRole(dto.role());

        psy = psychologistRepository.save(psy);

        return psychologistMapper.psychologistToDTO(psy);
    }

    @Transactional(timeout = 30)
    public void delete(Long id) {
        log.info("The method that delete the user of id: {} was started", id);
        psychologistRepository.deleteById(id);
    }

    @Transactional(readOnly = true, timeout = 15)
    public PsychologistRequestDTO findOne(Long id) {
        log.info("The method that finds a psychologist of id: {} started", id);
        Psychologist psychologist = psychologistRepository.findById(id).get();
        return psychologistMapper.psychologistToDTO(psychologist);
    }

    @Transactional(readOnly = true, timeout = 15)
    public Page<PsychologistRequestDTO> listAll(Pageable pageable) {
        log.info("The method that lists all the psychologists");
        return psychologistMapper.listToPsychologistDTO(psychologistRepository.findAll(pageable));
    }

    // @Transactional(readOnly = true, timeout = 15)
    // public Optional<Psychologist> findByUserId(Long userId) {
    //     return psychologistRepository.findByUserId(userId);
    // }

}
