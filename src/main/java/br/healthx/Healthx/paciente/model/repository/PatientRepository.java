package br.healthx.Healthx.paciente.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.healthx.Healthx.paciente.model.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public List<Patient> findByNameContainingIgnoreCase(String name);

}
