package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Downloads;

import io.github.jhipster.application.repository.DownloadsRepository;
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
 * REST controller for managing Downloads.
 */
@RestController
@RequestMapping("/api")
public class DownloadsResource {

    private final Logger log = LoggerFactory.getLogger(DownloadsResource.class);

    private static final String ENTITY_NAME = "downloads";

    private final DownloadsRepository downloadsRepository;

    public DownloadsResource(DownloadsRepository downloadsRepository) {
        this.downloadsRepository = downloadsRepository;
    }

    /**
     * POST  /downloads : Create a new downloads.
     *
     * @param downloads the downloads to create
     * @return the ResponseEntity with status 201 (Created) and with body the new downloads, or with status 400 (Bad Request) if the downloads has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/downloads")
    @Timed
    public ResponseEntity<Downloads> createDownloads(@RequestBody Downloads downloads) throws URISyntaxException {
        log.debug("REST request to save Downloads : {}", downloads);
        if (downloads.getId() != null) {
            throw new BadRequestAlertException("A new downloads cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Downloads result = downloadsRepository.save(downloads);
        return ResponseEntity.created(new URI("/api/downloads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /downloads : Updates an existing downloads.
     *
     * @param downloads the downloads to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated downloads,
     * or with status 400 (Bad Request) if the downloads is not valid,
     * or with status 500 (Internal Server Error) if the downloads couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/downloads")
    @Timed
    public ResponseEntity<Downloads> updateDownloads(@RequestBody Downloads downloads) throws URISyntaxException {
        log.debug("REST request to update Downloads : {}", downloads);
        if (downloads.getId() == null) {
            return createDownloads(downloads);
        }
        Downloads result = downloadsRepository.save(downloads);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, downloads.getId().toString()))
            .body(result);
    }

    /**
     * GET  /downloads : get all the downloads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of downloads in body
     */
    @GetMapping("/downloads")
    @Timed
    public List<Downloads> getAllDownloads() {
        log.debug("REST request to get all Downloads");
        return downloadsRepository.findAll();
        }

    /**
     * GET  /downloads/:id : get the "id" downloads.
     *
     * @param id the id of the downloads to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the downloads, or with status 404 (Not Found)
     */
    @GetMapping("/downloads/{id}")
    @Timed
    public ResponseEntity<Downloads> getDownloads(@PathVariable Long id) {
        log.debug("REST request to get Downloads : {}", id);
        Downloads downloads = downloadsRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(downloads));
    }

    /**
     * DELETE  /downloads/:id : delete the "id" downloads.
     *
     * @param id the id of the downloads to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/downloads/{id}")
    @Timed
    public ResponseEntity<Void> deleteDownloads(@PathVariable Long id) {
        log.debug("REST request to delete Downloads : {}", id);
        downloadsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
