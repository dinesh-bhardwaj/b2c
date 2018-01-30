package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Downloads;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Downloads entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DownloadsRepository extends JpaRepository<Downloads, Long> {

}
