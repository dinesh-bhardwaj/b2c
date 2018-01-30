package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.B2CApp;

import io.github.jhipster.application.domain.SchoolClass;
import io.github.jhipster.application.repository.SchoolClassRepository;
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
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SchoolClassResource REST controller.
 *
 * @see SchoolClassResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = B2CApp.class)
public class SchoolClassResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSchoolClassMockMvc;

    private SchoolClass schoolClass;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SchoolClassResource schoolClassResource = new SchoolClassResource(schoolClassRepository);
        this.restSchoolClassMockMvc = MockMvcBuilders.standaloneSetup(schoolClassResource)
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
    public static SchoolClass createEntity(EntityManager em) {
        SchoolClass schoolClass = new SchoolClass()
            .name(DEFAULT_NAME);
        return schoolClass;
    }

    @Before
    public void initTest() {
        schoolClass = createEntity(em);
    }

    @Test
    @Transactional
    public void createSchoolClass() throws Exception {
        int databaseSizeBeforeCreate = schoolClassRepository.findAll().size();

        // Create the SchoolClass
        restSchoolClassMockMvc.perform(post("/api/school-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(schoolClass)))
            .andExpect(status().isCreated());

        // Validate the SchoolClass in the database
        List<SchoolClass> schoolClassList = schoolClassRepository.findAll();
        assertThat(schoolClassList).hasSize(databaseSizeBeforeCreate + 1);
        SchoolClass testSchoolClass = schoolClassList.get(schoolClassList.size() - 1);
        assertThat(testSchoolClass.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createSchoolClassWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = schoolClassRepository.findAll().size();

        // Create the SchoolClass with an existing ID
        schoolClass.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSchoolClassMockMvc.perform(post("/api/school-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(schoolClass)))
            .andExpect(status().isBadRequest());

        // Validate the SchoolClass in the database
        List<SchoolClass> schoolClassList = schoolClassRepository.findAll();
        assertThat(schoolClassList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSchoolClasses() throws Exception {
        // Initialize the database
        schoolClassRepository.saveAndFlush(schoolClass);

        // Get all the schoolClassList
        restSchoolClassMockMvc.perform(get("/api/school-classes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(schoolClass.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getSchoolClass() throws Exception {
        // Initialize the database
        schoolClassRepository.saveAndFlush(schoolClass);

        // Get the schoolClass
        restSchoolClassMockMvc.perform(get("/api/school-classes/{id}", schoolClass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(schoolClass.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSchoolClass() throws Exception {
        // Get the schoolClass
        restSchoolClassMockMvc.perform(get("/api/school-classes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSchoolClass() throws Exception {
        // Initialize the database
        schoolClassRepository.saveAndFlush(schoolClass);
        int databaseSizeBeforeUpdate = schoolClassRepository.findAll().size();

        // Update the schoolClass
        SchoolClass updatedSchoolClass = schoolClassRepository.findOne(schoolClass.getId());
        // Disconnect from session so that the updates on updatedSchoolClass are not directly saved in db
        em.detach(updatedSchoolClass);
        updatedSchoolClass
            .name(UPDATED_NAME);

        restSchoolClassMockMvc.perform(put("/api/school-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSchoolClass)))
            .andExpect(status().isOk());

        // Validate the SchoolClass in the database
        List<SchoolClass> schoolClassList = schoolClassRepository.findAll();
        assertThat(schoolClassList).hasSize(databaseSizeBeforeUpdate);
        SchoolClass testSchoolClass = schoolClassList.get(schoolClassList.size() - 1);
        assertThat(testSchoolClass.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingSchoolClass() throws Exception {
        int databaseSizeBeforeUpdate = schoolClassRepository.findAll().size();

        // Create the SchoolClass

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSchoolClassMockMvc.perform(put("/api/school-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(schoolClass)))
            .andExpect(status().isCreated());

        // Validate the SchoolClass in the database
        List<SchoolClass> schoolClassList = schoolClassRepository.findAll();
        assertThat(schoolClassList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSchoolClass() throws Exception {
        // Initialize the database
        schoolClassRepository.saveAndFlush(schoolClass);
        int databaseSizeBeforeDelete = schoolClassRepository.findAll().size();

        // Get the schoolClass
        restSchoolClassMockMvc.perform(delete("/api/school-classes/{id}", schoolClass.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SchoolClass> schoolClassList = schoolClassRepository.findAll();
        assertThat(schoolClassList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SchoolClass.class);
        SchoolClass schoolClass1 = new SchoolClass();
        schoolClass1.setId(1L);
        SchoolClass schoolClass2 = new SchoolClass();
        schoolClass2.setId(schoolClass1.getId());
        assertThat(schoolClass1).isEqualTo(schoolClass2);
        schoolClass2.setId(2L);
        assertThat(schoolClass1).isNotEqualTo(schoolClass2);
        schoolClass1.setId(null);
        assertThat(schoolClass1).isNotEqualTo(schoolClass2);
    }
}
