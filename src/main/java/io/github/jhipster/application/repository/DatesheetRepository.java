package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Datesheet;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Datesheet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DatesheetRepository extends JpaRepository<Datesheet, Long> {

}
