package br.healthx.Healthx.psychologist.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.healthx.Healthx.psychologist.model.entity.Psychologist;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
}
