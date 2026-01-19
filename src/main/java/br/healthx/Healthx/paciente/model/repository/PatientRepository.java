package br.healthx.Healthx.paciente.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.healthx.Healthx.paciente.model.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
