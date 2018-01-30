package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.B2CApp;

import io.github.jhipster.application.domain.Datesheet;
import io.github.jhipster.application.repository.DatesheetRepository;
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
 * Test class for the DatesheetResource REST controller.
 *
 * @see DatesheetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = B2CApp.class)
public class DatesheetResourceIntTest {

    private static final String DEFAULT_EXAM = "AAAAAAAAAA";
    private static final String UPDATED_EXAM = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DatesheetRepository datesheetRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDatesheetMockMvc;

    private Datesheet datesheet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DatesheetResource datesheetResource = new DatesheetResource(datesheetRepository);
        this.restDatesheetMockMvc = MockMvcBuilders.standaloneSetup(datesheetResource)
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
    public static Datesheet createEntity(EntityManager em) {
        Datesheet datesheet = new Datesheet()
            .exam(DEFAULT_EXAM)
            .image(DEFAULT_IMAGE)
            .date(DEFAULT_DATE);
        return datesheet;
    }

    @Before
    public void initTest() {
        datesheet = createEntity(em);
    }

    @Test
    @Transactional
    public void createDatesheet() throws Exception {
        int databaseSizeBeforeCreate = datesheetRepository.findAll().size();

        // Create the Datesheet
        restDatesheetMockMvc.perform(post("/api/datesheets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datesheet)))
            .andExpect(status().isCreated());

        // Validate the Datesheet in the database
        List<Datesheet> datesheetList = datesheetRepository.findAll();
        assertThat(datesheetList).hasSize(databaseSizeBeforeCreate + 1);
        Datesheet testDatesheet = datesheetList.get(datesheetList.size() - 1);
        assertThat(testDatesheet.getExam()).isEqualTo(DEFAULT_EXAM);
        assertThat(testDatesheet.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testDatesheet.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createDatesheetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = datesheetRepository.findAll().size();

        // Create the Datesheet with an existing ID
        datesheet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDatesheetMockMvc.perform(post("/api/datesheets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datesheet)))
            .andExpect(status().isBadRequest());

        // Validate the Datesheet in the database
        List<Datesheet> datesheetList = datesheetRepository.findAll();
        assertThat(datesheetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDatesheets() throws Exception {
        // Initialize the database
        datesheetRepository.saveAndFlush(datesheet);

        // Get all the datesheetList
        restDatesheetMockMvc.perform(get("/api/datesheets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(datesheet.getId().intValue())))
            .andExpect(jsonPath("$.[*].exam").value(hasItem(DEFAULT_EXAM.toString())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    public void getDatesheet() throws Exception {
        // Initialize the database
        datesheetRepository.saveAndFlush(datesheet);

        // Get the datesheet
        restDatesheetMockMvc.perform(get("/api/datesheets/{id}", datesheet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(datesheet.getId().intValue()))
            .andExpect(jsonPath("$.exam").value(DEFAULT_EXAM.toString()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDatesheet() throws Exception {
        // Get the datesheet
        restDatesheetMockMvc.perform(get("/api/datesheets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDatesheet() throws Exception {
        // Initialize the database
        datesheetRepository.saveAndFlush(datesheet);
        int databaseSizeBeforeUpdate = datesheetRepository.findAll().size();

        // Update the datesheet
        Datesheet updatedDatesheet = datesheetRepository.findOne(datesheet.getId());
        // Disconnect from session so that the updates on updatedDatesheet are not directly saved in db
        em.detach(updatedDatesheet);
        updatedDatesheet
            .exam(UPDATED_EXAM)
            .image(UPDATED_IMAGE)
            .date(UPDATED_DATE);

        restDatesheetMockMvc.perform(put("/api/datesheets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDatesheet)))
            .andExpect(status().isOk());

        // Validate the Datesheet in the database
        List<Datesheet> datesheetList = datesheetRepository.findAll();
        assertThat(datesheetList).hasSize(databaseSizeBeforeUpdate);
        Datesheet testDatesheet = datesheetList.get(datesheetList.size() - 1);
        assertThat(testDatesheet.getExam()).isEqualTo(UPDATED_EXAM);
        assertThat(testDatesheet.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testDatesheet.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDatesheet() throws Exception {
        int databaseSizeBeforeUpdate = datesheetRepository.findAll().size();

        // Create the Datesheet

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDatesheetMockMvc.perform(put("/api/datesheets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datesheet)))
            .andExpect(status().isCreated());

        // Validate the Datesheet in the database
        List<Datesheet> datesheetList = datesheetRepository.findAll();
        assertThat(datesheetList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDatesheet() throws Exception {
        // Initialize the database
        datesheetRepository.saveAndFlush(datesheet);
        int databaseSizeBeforeDelete = datesheetRepository.findAll().size();

        // Get the datesheet
        restDatesheetMockMvc.perform(delete("/api/datesheets/{id}", datesheet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Datesheet> datesheetList = datesheetRepository.findAll();
        assertThat(datesheetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Datesheet.class);
        Datesheet datesheet1 = new Datesheet();
        datesheet1.setId(1L);
        Datesheet datesheet2 = new Datesheet();
        datesheet2.setId(datesheet1.getId());
        assertThat(datesheet1).isEqualTo(datesheet2);
        datesheet2.setId(2L);
        assertThat(datesheet1).isNotEqualTo(datesheet2);
        datesheet1.setId(null);
        assertThat(datesheet1).isNotEqualTo(datesheet2);
    }
}
