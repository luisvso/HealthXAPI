package br.healthx.Healthx.paciente.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.healthx.Healthx.paciente.dto.RequestPatientDTO;
import br.healthx.Healthx.paciente.dto.ResponsePatientDTO;
import br.healthx.Healthx.paciente.model.entity.Gender;
import br.healthx.Healthx.paciente.model.entity.Patient;
import br.healthx.Healthx.paciente.model.entity.Race;

@Component
public class PatientMapper {

    public Patient toEntity(RequestPatientDTO dto) {

        Patient patient = new Patient();

        patient.setEmail(dto.email());
        patient.setBirthDate(dto.birthDate());
        patient.setName(dto.name());
        patient.setGender(dto.gender());
        patient.setPhone(dto.phone());
        patient.setGuardian(dto.guardian());
        patient.setRace(dto.race());
        patient.setCreateAt(LocalDate.now());

        return patient;
    }

    public ResponsePatientDTO entityToResponse(Patient patient) {
        String email = patient.getEmail();
        LocalDate birthDate = patient.getBirthDate();
        String name = patient.getName();
        Gender gender = patient.getGender();
        String phone = patient.getPhone();
        String guardian = patient.getGuardian();
        Race race = patient.getRace();
        LocalDate createAt = patient.getCreateAt();

        ResponsePatientDTO dto = new ResponsePatientDTO(name, birthDate, gender, guardian, race, email,
                phone, createAt);

        return dto;
    }

    public List<ResponsePatientDTO> toResponseList(List<Patient> patients) {
        List<ResponsePatientDTO> responseList = patients.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());

        return responseList;
    }

}
