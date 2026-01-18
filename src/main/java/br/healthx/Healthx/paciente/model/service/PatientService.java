package br.healthx.Healthx.paciente.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
    public ResponsePatientDTO create(RequestPatientDTO dto) {
        patientValidation.validatingPatient(dto);
        Patient paciente = patientMapper.toEntity(dto);
        paciente = patientRepository.save(paciente);

        return patientMapper.entityToResponse(paciente);

    }

    public void delete(Long id) {
        patientValidation.existId(id);
        patientRepository.deleteById(id);
    }

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

    public ResponsePatientDTO findById(Long id) {
        patientValidation.existId(id);
        return patientMapper.entityToResponse(patientRepository.findById(id).get());
    }

    public List<ResponsePatientDTO> findByName(String name) {
        return patientMapper.toResponseList(patientRepository.findByNameContainingIgnoreCase(name));
    }

    public List<ResponsePatientDTO> findAll() {
        return patientMapper.toResponseList(patientRepository.findAll());
    }
}
