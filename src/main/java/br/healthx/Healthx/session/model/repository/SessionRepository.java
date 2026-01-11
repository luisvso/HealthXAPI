package br.healthx.Healthx.session.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.healthx.Healthx.session.model.entity.Session;
import br.healthx.Healthx.session.model.entity.SessionType;
import br.healthx.Healthx.session.model.entity.Status;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    public List<Session> findByStatus(Status status);

    public List<Session> findBySessionType(SessionType sessionType);

    public List<Session> findByPatient_NameContainingIgnoreCase(String name);

    public List<Session> findByStartDateBetween(LocalDate start, LocalDate end);
}
