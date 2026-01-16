package br.healthx.Healthx.session.model.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.healthx.Healthx.session.model.entity.Session;
import br.healthx.Healthx.session.model.entity.SessionType;
import br.healthx.Healthx.session.model.entity.Status;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    public Page<Session> findByStatus(Status status, Pageable pageable);

    public Page<Session> findBySessionType(SessionType sessionType, Pageable pageable);

    public Page<Session> findByPatient_NameContainingIgnoreCase(String name, Pageable pageable);

    public Page<Session> findByStartDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
