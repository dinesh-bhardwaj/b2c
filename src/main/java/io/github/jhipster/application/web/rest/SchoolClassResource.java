package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.SchoolClass;

import io.github.jhipster.application.repository.SchoolClassRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SchoolClass.
 */
@RestController
@RequestMapping("/api")
public class SchoolClassResource {

    private final Logger log = LoggerFactory.getLogger(SchoolClassResource.class);

    private static final String ENTITY_NAME = "schoolClass";

    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassResource(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    /**
     * POST  /school-classes : Create a new schoolClass.
     *
     * @param schoolClass the schoolClass to create
     * @return the ResponseEntity with status 201 (Created) and with body the new schoolClass, or with status 400 (Bad Request) if the schoolClass has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/school-classes")
    @Timed
    public ResponseEntity<SchoolClass> createSchoolClass(@RequestBody SchoolClass schoolClass) throws URISyntaxException {
        log.debug("REST request to save SchoolClass : {}", schoolClass);
        if (schoolClass.getId() != null) {
            throw new BadRequestAlertException("A new schoolClass cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SchoolClass result = schoolClassRepository.save(schoolClass);
        return ResponseEntity.created(new URI("/api/school-classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /school-classes : Updates an existing schoolClass.
     *
     * @param schoolClass the schoolClass to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated schoolClass,
     * or with status 400 (Bad Request) if the schoolClass is not valid,
     * or with status 500 (Internal Server Error) if the schoolClass couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/school-classes")
    @Timed
    public ResponseEntity<SchoolClass> updateSchoolClass(@RequestBody SchoolClass schoolClass) throws URISyntaxException {
        log.debug("REST request to update SchoolClass : {}", schoolClass);
        if (schoolClass.getId() == null) {
            return createSchoolClass(schoolClass);
        }
        SchoolClass result = schoolClassRepository.save(schoolClass);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, schoolClass.getId().toString()))
            .body(result);
    }

    /**
     * GET  /school-classes : get all the schoolClasses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of schoolClasses in body
     */
    @GetMapping("/school-classes")
    @Timed
    public List<SchoolClass> getAllSchoolClasses() {
        log.debug("REST request to get all SchoolClasses");
        return schoolClassRepository.findAll();
        }

    /**
     * GET  /school-classes/:id : get the "id" schoolClass.
     *
     * @param id the id of the schoolClass to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the schoolClass, or with status 404 (Not Found)
     */
    @GetMapping("/school-classes/{id}")
    @Timed
    public ResponseEntity<SchoolClass> getSchoolClass(@PathVariable Long id) {
        log.debug("REST request to get SchoolClass : {}", id);
        SchoolClass schoolClass = schoolClassRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(schoolClass));
    }

    /**
     * DELETE  /school-classes/:id : delete the "id" schoolClass.
     *
     * @param id the id of the schoolClass to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/school-classes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSchoolClass(@PathVariable Long id) {
        log.debug("REST request to delete SchoolClass : {}", id);
        schoolClassRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
