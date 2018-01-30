package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Datesheet;

import io.github.jhipster.application.repository.DatesheetRepository;
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
 * REST controller for managing Datesheet.
 */
@RestController
@RequestMapping("/api")
public class DatesheetResource {

    private final Logger log = LoggerFactory.getLogger(DatesheetResource.class);

    private static final String ENTITY_NAME = "datesheet";

    private final DatesheetRepository datesheetRepository;

    public DatesheetResource(DatesheetRepository datesheetRepository) {
        this.datesheetRepository = datesheetRepository;
    }

    /**
     * POST  /datesheets : Create a new datesheet.
     *
     * @param datesheet the datesheet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new datesheet, or with status 400 (Bad Request) if the datesheet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/datesheets")
    @Timed
    public ResponseEntity<Datesheet> createDatesheet(@RequestBody Datesheet datesheet) throws URISyntaxException {
        log.debug("REST request to save Datesheet : {}", datesheet);
        if (datesheet.getId() != null) {
            throw new BadRequestAlertException("A new datesheet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Datesheet result = datesheetRepository.save(datesheet);
        return ResponseEntity.created(new URI("/api/datesheets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /datesheets : Updates an existing datesheet.
     *
     * @param datesheet the datesheet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated datesheet,
     * or with status 400 (Bad Request) if the datesheet is not valid,
     * or with status 500 (Internal Server Error) if the datesheet couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/datesheets")
    @Timed
    public ResponseEntity<Datesheet> updateDatesheet(@RequestBody Datesheet datesheet) throws URISyntaxException {
        log.debug("REST request to update Datesheet : {}", datesheet);
        if (datesheet.getId() == null) {
            return createDatesheet(datesheet);
        }
        Datesheet result = datesheetRepository.save(datesheet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, datesheet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /datesheets : get all the datesheets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of datesheets in body
     */
    @GetMapping("/datesheets")
    @Timed
    public List<Datesheet> getAllDatesheets() {
        log.debug("REST request to get all Datesheets");
        return datesheetRepository.findAll();
        }

    /**
     * GET  /datesheets/:id : get the "id" datesheet.
     *
     * @param id the id of the datesheet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the datesheet, or with status 404 (Not Found)
     */
    @GetMapping("/datesheets/{id}")
    @Timed
    public ResponseEntity<Datesheet> getDatesheet(@PathVariable Long id) {
        log.debug("REST request to get Datesheet : {}", id);
        Datesheet datesheet = datesheetRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(datesheet));
    }

    /**
     * DELETE  /datesheets/:id : delete the "id" datesheet.
     *
     * @param id the id of the datesheet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/datesheets/{id}")
    @Timed
    public ResponseEntity<Void> deleteDatesheet(@PathVariable Long id) {
        log.debug("REST request to delete Datesheet : {}", id);
        datesheetRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
