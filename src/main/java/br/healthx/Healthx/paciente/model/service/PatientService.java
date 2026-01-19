package br.healthx.Healthx.paciente.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.healthx.Healthx.paciente.dto.RequestPatientDTO;
import br.healthx.Healthx.paciente.dto.ResponsePatientDTO;
import br.healthx.Healthx.paciente.mapper.PatientMapper;
import br.healthx.Healthx.paciente.model.entity.Patient;
import br.healthx.Healthx.paciente.model.repository.PatientRepository;
import br.healthx.Healthx.paciente.model.validation.PatientValidation;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientValidation patientValidation;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientValidation patientValidation,
            PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientValidation = patientValidation;
        this.patientMapper = patientMapper;
    }

    /// method for creating Paciente
    @Transactional(timeout = 30)
    public ResponsePatientDTO create(RequestPatientDTO dto) {
        patientValidation.validatingPatient(dto);
        Patient paciente = patientMapper.toEntity(dto);
        paciente = patientRepository.save(paciente);

        return patientMapper.entityToResponse(paciente);

    }

    @Transactional(timeout = 30)
    public void delete(Long id) {
        patientValidation.existId(id);
        patientRepository.deleteById(id);
    }

    @Transactional(timeout = 30)
    public ResponsePatientDTO update(Long id, RequestPatientDTO dto) {
        Patient patient = patientRepository.findById(id).get();

        patient.setName(dto.name());
        patient.setEmail(dto.email());
        patient.setBirthDate(dto.birthDate());
        patient.setGender(dto.gender());
        patient.setPhone(dto.phone());
        patient.setGuardian(dto.guardian());
        patient.setRace(dto.race());

        patient = patientRepository.save(patient);

        return patientMapper.entityToResponse(patient);

    }

    @Transactional(readOnly = true, timeout = 15)
    public ResponsePatientDTO findById(Long id) {
        patientValidation.existId(id);
        return patientMapper.entityToResponse(patientRepository.findById(id).get());
    }

    @Transactional(readOnly = true, timeout = 15)
    public Page<ResponsePatientDTO> findByName(String name, Pageable pageable) {
        return patientMapper.toResponseList(patientRepository.findByNameContainingIgnoreCase(name, pageable));
    }

    @Transactional(readOnly = true, timeout = 15)
    public Page<ResponsePatientDTO> findAll(Pageable pageable) {
        return patientMapper.toResponseList(patientRepository.findAll(pageable));
    }
}
