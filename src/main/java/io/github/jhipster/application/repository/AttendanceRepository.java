package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Attendance;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Attendance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
