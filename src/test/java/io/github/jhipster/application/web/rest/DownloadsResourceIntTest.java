package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.B2CApp;

import io.github.jhipster.application.domain.Downloads;
import io.github.jhipster.application.repository.DownloadsRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DownloadsResource REST controller.
 *
 * @see DownloadsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = B2CApp.class)
public class DownloadsResourceIntTest {

    private static final String DEFAULT_EXAM = "AAAAAAAAAA";
    private static final String UPDATED_EXAM = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DownloadsRepository downloadsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDownloadsMockMvc;

    private Downloads downloads;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DownloadsResource downloadsResource = new DownloadsResource(downloadsRepository);
        this.restDownloadsMockMvc = MockMvcBuilders.standaloneSetup(downloadsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Downloads createEntity(EntityManager em) {
        Downloads downloads = new Downloads()
            .exam(DEFAULT_EXAM)
            .image(DEFAULT_IMAGE)
            .date(DEFAULT_DATE);
        return downloads;
    }

    @Before
    public void initTest() {
        downloads = createEntity(em);
    }

    @Test
    @Transactional
    public void createDownloads() throws Exception {
        int databaseSizeBeforeCreate = downloadsRepository.findAll().size();

        // Create the Downloads
        restDownloadsMockMvc.perform(post("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloads)))
            .andExpect(status().isCreated());

        // Validate the Downloads in the database
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeCreate + 1);
        Downloads testDownloads = downloadsList.get(downloadsList.size() - 1);
        assertThat(testDownloads.getExam()).isEqualTo(DEFAULT_EXAM);
        assertThat(testDownloads.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testDownloads.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createDownloadsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = downloadsRepository.findAll().size();

        // Create the Downloads with an existing ID
        downloads.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDownloadsMockMvc.perform(post("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloads)))
            .andExpect(status().isBadRequest());

        // Validate the Downloads in the database
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDownloads() throws Exception {
        // Initialize the database
        downloadsRepository.saveAndFlush(downloads);

        // Get all the downloadsList
        restDownloadsMockMvc.perform(get("/api/downloads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(downloads.getId().intValue())))
            .andExpect(jsonPath("$.[*].exam").value(hasItem(DEFAULT_EXAM.toString())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    public void getDownloads() throws Exception {
        // Initialize the database
        downloadsRepository.saveAndFlush(downloads);

        // Get the downloads
        restDownloadsMockMvc.perform(get("/api/downloads/{id}", downloads.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(downloads.getId().intValue()))
            .andExpect(jsonPath("$.exam").value(DEFAULT_EXAM.toString()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDownloads() throws Exception {
        // Get the downloads
        restDownloadsMockMvc.perform(get("/api/downloads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDownloads() throws Exception {
        // Initialize the database
        downloadsRepository.saveAndFlush(downloads);
        int databaseSizeBeforeUpdate = downloadsRepository.findAll().size();

        // Update the downloads
        Downloads updatedDownloads = downloadsRepository.findOne(downloads.getId());
        // Disconnect from session so that the updates on updatedDownloads are not directly saved in db
        em.detach(updatedDownloads);
        updatedDownloads
            .exam(UPDATED_EXAM)
            .image(UPDATED_IMAGE)
            .date(UPDATED_DATE);

        restDownloadsMockMvc.perform(put("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDownloads)))
            .andExpect(status().isOk());

        // Validate the Downloads in the database
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeUpdate);
        Downloads testDownloads = downloadsList.get(downloadsList.size() - 1);
        assertThat(testDownloads.getExam()).isEqualTo(UPDATED_EXAM);
        assertThat(testDownloads.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testDownloads.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDownloads() throws Exception {
        int databaseSizeBeforeUpdate = downloadsRepository.findAll().size();

        // Create the Downloads

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDownloadsMockMvc.perform(put("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloads)))
            .andExpect(status().isCreated());

        // Validate the Downloads in the database
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDownloads() throws Exception {
        // Initialize the database
        downloadsRepository.saveAndFlush(downloads);
        int databaseSizeBeforeDelete = downloadsRepository.findAll().size();

        // Get the downloads
        restDownloadsMockMvc.perform(delete("/api/downloads/{id}", downloads.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Downloads.class);
        Downloads downloads1 = new Downloads();
        downloads1.setId(1L);
        Downloads downloads2 = new Downloads();
        downloads2.setId(downloads1.getId());
        assertThat(downloads1).isEqualTo(downloads2);
        downloads2.setId(2L);
        assertThat(downloads1).isNotEqualTo(downloads2);
        downloads1.setId(null);
        assertThat(downloads1).isNotEqualTo(downloads2);
    }
}
