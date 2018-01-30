package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Session;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Session entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
