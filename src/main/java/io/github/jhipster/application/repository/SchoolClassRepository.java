package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.SchoolClass;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SchoolClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

}
