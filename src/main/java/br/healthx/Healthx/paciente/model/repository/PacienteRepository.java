package br.healthx.Healthx.paciente.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.healthx.Healthx.paciente.model.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
