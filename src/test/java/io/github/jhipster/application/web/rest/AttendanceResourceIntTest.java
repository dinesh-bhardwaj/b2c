package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.B2CApp;

import io.github.jhipster.application.domain.Attendance;
import io.github.jhipster.application.repository.AttendanceRepository;
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

import io.github.jhipster.application.domain.enumeration.MonthFields;
import io.github.jhipster.application.domain.enumeration.AttendanceField;
/**
 * Test class for the AttendanceResource REST controller.
 *
 * @see AttendanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = B2CApp.class)
public class AttendanceResourceIntTest {

    private static final MonthFields DEFAULT_MONTH = MonthFields.JAN;
    private static final MonthFields UPDATED_MONTH = MonthFields.FEB;

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final AttendanceField DEFAULT_ATTENDANCE = AttendanceField.PRESENT;
    private static final AttendanceField UPDATED_ATTENDANCE = AttendanceField.ABSENT;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttendanceMockMvc;

    private Attendance attendance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttendanceResource attendanceResource = new AttendanceResource(attendanceRepository);
        this.restAttendanceMockMvc = MockMvcBuilders.standaloneSetup(attendanceResource)
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
    public static Attendance createEntity(EntityManager em) {
        Attendance attendance = new Attendance()
            .month(DEFAULT_MONTH)
            .date(DEFAULT_DATE)
            .attendance(DEFAULT_ATTENDANCE);
        return attendance;
    }

    @Before
    public void initTest() {
        attendance = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttendance() throws Exception {
        int databaseSizeBeforeCreate = attendanceRepository.findAll().size();

        // Create the Attendance
        restAttendanceMockMvc.perform(post("/api/attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendance)))
            .andExpect(status().isCreated());

        // Validate the Attendance in the database
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeCreate + 1);
        Attendance testAttendance = attendanceList.get(attendanceList.size() - 1);
        assertThat(testAttendance.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testAttendance.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testAttendance.getAttendance()).isEqualTo(DEFAULT_ATTENDANCE);
    }

    @Test
    @Transactional
    public void createAttendanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attendanceRepository.findAll().size();

        // Create the Attendance with an existing ID
        attendance.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttendanceMockMvc.perform(post("/api/attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendance)))
            .andExpect(status().isBadRequest());

        // Validate the Attendance in the database
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAttendances() throws Exception {
        // Initialize the database
        attendanceRepository.saveAndFlush(attendance);

        // Get all the attendanceList
        restAttendanceMockMvc.perform(get("/api/attendances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attendance.getId().intValue())))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].attendance").value(hasItem(DEFAULT_ATTENDANCE.toString())));
    }

    @Test
    @Transactional
    public void getAttendance() throws Exception {
        // Initialize the database
        attendanceRepository.saveAndFlush(attendance);

        // Get the attendance
        restAttendanceMockMvc.perform(get("/api/attendances/{id}", attendance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attendance.getId().intValue()))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.attendance").value(DEFAULT_ATTENDANCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAttendance() throws Exception {
        // Get the attendance
        restAttendanceMockMvc.perform(get("/api/attendances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttendance() throws Exception {
        // Initialize the database
        attendanceRepository.saveAndFlush(attendance);
        int databaseSizeBeforeUpdate = attendanceRepository.findAll().size();

        // Update the attendance
        Attendance updatedAttendance = attendanceRepository.findOne(attendance.getId());
        // Disconnect from session so that the updates on updatedAttendance are not directly saved in db
        em.detach(updatedAttendance);
        updatedAttendance
            .month(UPDATED_MONTH)
            .date(UPDATED_DATE)
            .attendance(UPDATED_ATTENDANCE);

        restAttendanceMockMvc.perform(put("/api/attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttendance)))
            .andExpect(status().isOk());

        // Validate the Attendance in the database
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeUpdate);
        Attendance testAttendance = attendanceList.get(attendanceList.size() - 1);
        assertThat(testAttendance.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testAttendance.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testAttendance.getAttendance()).isEqualTo(UPDATED_ATTENDANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingAttendance() throws Exception {
        int databaseSizeBeforeUpdate = attendanceRepository.findAll().size();

        // Create the Attendance

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAttendanceMockMvc.perform(put("/api/attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendance)))
            .andExpect(status().isCreated());

        // Validate the Attendance in the database
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAttendance() throws Exception {
        // Initialize the database
        attendanceRepository.saveAndFlush(attendance);
        int databaseSizeBeforeDelete = attendanceRepository.findAll().size();

        // Get the attendance
        restAttendanceMockMvc.perform(delete("/api/attendances/{id}", attendance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Attendance.class);
        Attendance attendance1 = new Attendance();
        attendance1.setId(1L);
        Attendance attendance2 = new Attendance();
        attendance2.setId(attendance1.getId());
        assertThat(attendance1).isEqualTo(attendance2);
        attendance2.setId(2L);
        assertThat(attendance1).isNotEqualTo(attendance2);
        attendance1.setId(null);
        assertThat(attendance1).isNotEqualTo(attendance2);
    }
}
