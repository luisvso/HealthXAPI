package br.healthx.Healthx.session.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.healthx.Healthx.session.model.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
