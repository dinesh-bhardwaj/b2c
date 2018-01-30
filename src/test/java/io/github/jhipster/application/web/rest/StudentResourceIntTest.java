package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.B2CApp;

import io.github.jhipster.application.domain.Student;
import io.github.jhipster.application.repository.StudentRepository;
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

import io.github.jhipster.application.domain.enumeration.ProfileSelection;
import io.github.jhipster.application.domain.enumeration.GenderSelection;
import io.github.jhipster.application.domain.enumeration.ReligionField;
import io.github.jhipster.application.domain.enumeration.CategoryField;
import io.github.jhipster.application.domain.enumeration.PreviousClassField;
import io.github.jhipster.application.domain.enumeration.Previousyear;
import io.github.jhipster.application.domain.enumeration.Disability;
import io.github.jhipster.application.domain.enumeration.YESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;
/**
 * Test class for the StudentResource REST controller.
 *
 * @see StudentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = B2CApp.class)
public class StudentResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SCHOOL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADMISSION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ADMISSION_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_ROLL_NO = 1;
    private static final Integer UPDATED_ROLL_NO = 2;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final ProfileSelection DEFAULT_PROFILE = ProfileSelection.STUDENT;
    private static final ProfileSelection UPDATED_PROFILE = ProfileSelection.TEACHER;

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final GenderSelection DEFAULT_GENDER = GenderSelection.MALE;
    private static final GenderSelection UPDATED_GENDER = GenderSelection.FEMALE;

    private static final String DEFAULT_LOCALITY = "AAAAAAAAAA";
    private static final String UPDATED_LOCALITY = "BBBBBBBBBB";

    private static final String DEFAULT_AADHAR = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_OF_ADMISSION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF_ADMISSION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ReligionField DEFAULT_RELIGION = ReligionField.HINDU;
    private static final ReligionField UPDATED_RELIGION = ReligionField.MUSLIM;

    private static final CategoryField DEFAULT_CATEGORY = CategoryField.GENERAL;
    private static final CategoryField UPDATED_CATEGORY = CategoryField.SC;

    private static final PreviousClassField DEFAULT_PREVIOUS_CLASS = PreviousClassField.PRE_PRIMARY;
    private static final PreviousClassField UPDATED_PREVIOUS_CLASS = PreviousClassField.FIRST;

    private static final Previousyear DEFAULT_PREVIOUS_YEAR = Previousyear.SAME_SCHOOL;
    private static final Previousyear UPDATED_PREVIOUS_YEAR = Previousyear.ANOTHER_SCHOOL;

    private static final Disability DEFAULT_DISABILITY = Disability.BLINDNESS;
    private static final Disability UPDATED_DISABILITY = Disability.VISUAL_LOW_VISION;

    private static final YESNOSELECTION DEFAULT_BPL = YESNOSELECTION.YES;
    private static final YESNOSELECTION UPDATED_BPL = YESNOSELECTION.NO;

    private static final NAYESNOSELECTION DEFAULT_DISADVANTAGE = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_DISADVANTAGE = NAYESNOSELECTION.YES;

    private static final NAYESNOSELECTION DEFAULT_TRANSPORT = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_TRANSPORT = NAYESNOSELECTION.YES;

    private static final NAYESNOSELECTION DEFAULT_ESCORT = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_ESCORT = NAYESNOSELECTION.YES;

    private static final NAYESNOSELECTION DEFAULT_HOSTEL = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_HOSTEL = NAYESNOSELECTION.YES;

    private static final NAYESNOSELECTION DEFAULT_TRAINING = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_TRAINING = NAYESNOSELECTION.YES;

    private static final NAYESNOSELECTION DEFAULT_BOOK = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_BOOK = NAYESNOSELECTION.YES;

    private static final NAYESNOSELECTION DEFAULT_UNIFORMSETS = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_UNIFORMSETS = NAYESNOSELECTION.YES;

    private static final NAYESNOSELECTION DEFAULT_HOMELESS = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_HOMELESS = NAYESNOSELECTION.YES;

    private static final NAYESNOSELECTION DEFAULT_FREE_EDUCATION = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_FREE_EDUCATION = NAYESNOSELECTION.YES;

    private static final NAYESNOSELECTION DEFAULT_CWSN = NAYESNOSELECTION.NA;
    private static final NAYESNOSELECTION UPDATED_CWSN = NAYESNOSELECTION.YES;

    private static final String DEFAULT_DISEASE = "AAAAAAAAAA";
    private static final String UPDATED_DISEASE = "BBBBBBBBBB";

    private static final String DEFAULT_PRECAUTIONS = "AAAAAAAAAA";
    private static final String UPDATED_PRECAUTIONS = "BBBBBBBBBB";

    private static final Instant DEFAULT_ACTIVE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACTIVE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStudentMockMvc;

    private Student student;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StudentResource studentResource = new StudentResource(studentRepository);
        this.restStudentMockMvc = MockMvcBuilders.standaloneSetup(studentResource)
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
    public static Student createEntity(EntityManager em) {
        Student student = new Student()
            .name(DEFAULT_NAME)
            .fatherName(DEFAULT_FATHER_NAME)
            .motherName(DEFAULT_MOTHER_NAME)
            .dob(DEFAULT_DOB)
            .schoolName(DEFAULT_SCHOOL_NAME)
            .admissionNumber(DEFAULT_ADMISSION_NUMBER)
            .rollNo(DEFAULT_ROLL_NO)
            .email(DEFAULT_EMAIL)
            .address(DEFAULT_ADDRESS)
            .mobile(DEFAULT_MOBILE)
            .phone(DEFAULT_PHONE)
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .profile(DEFAULT_PROFILE)
            .image(DEFAULT_IMAGE)
            .gender(DEFAULT_GENDER)
            .locality(DEFAULT_LOCALITY)
            .aadhar(DEFAULT_AADHAR)
            .dateOfAdmission(DEFAULT_DATE_OF_ADMISSION)
            .religion(DEFAULT_RELIGION)
            .category(DEFAULT_CATEGORY)
            .previousClass(DEFAULT_PREVIOUS_CLASS)
            .previousYear(DEFAULT_PREVIOUS_YEAR)
            .disability(DEFAULT_DISABILITY)
            .bpl(DEFAULT_BPL)
            .disadvantage(DEFAULT_DISADVANTAGE)
            .transport(DEFAULT_TRANSPORT)
            .escort(DEFAULT_ESCORT)
            .hostel(DEFAULT_HOSTEL)
            .training(DEFAULT_TRAINING)
            .book(DEFAULT_BOOK)
            .uniformsets(DEFAULT_UNIFORMSETS)
            .homeless(DEFAULT_HOMELESS)
            .freeEducation(DEFAULT_FREE_EDUCATION)
            .cwsn(DEFAULT_CWSN)
            .disease(DEFAULT_DISEASE)
            .precautions(DEFAULT_PRECAUTIONS)
            .active(DEFAULT_ACTIVE)
            .isDeleted(DEFAULT_IS_DELETED);
        return student;
    }

    @Before
    public void initTest() {
        student = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // Create the Student
        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStudent.getFatherName()).isEqualTo(DEFAULT_FATHER_NAME);
        assertThat(testStudent.getMotherName()).isEqualTo(DEFAULT_MOTHER_NAME);
        assertThat(testStudent.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testStudent.getSchoolName()).isEqualTo(DEFAULT_SCHOOL_NAME);
        assertThat(testStudent.getAdmissionNumber()).isEqualTo(DEFAULT_ADMISSION_NUMBER);
        assertThat(testStudent.getRollNo()).isEqualTo(DEFAULT_ROLL_NO);
        assertThat(testStudent.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testStudent.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testStudent.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testStudent.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testStudent.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testStudent.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testStudent.getProfile()).isEqualTo(DEFAULT_PROFILE);
        assertThat(testStudent.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testStudent.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testStudent.getLocality()).isEqualTo(DEFAULT_LOCALITY);
        assertThat(testStudent.getAadhar()).isEqualTo(DEFAULT_AADHAR);
        assertThat(testStudent.getDateOfAdmission()).isEqualTo(DEFAULT_DATE_OF_ADMISSION);
        assertThat(testStudent.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testStudent.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testStudent.getPreviousClass()).isEqualTo(DEFAULT_PREVIOUS_CLASS);
        assertThat(testStudent.getPreviousYear()).isEqualTo(DEFAULT_PREVIOUS_YEAR);
        assertThat(testStudent.getDisability()).isEqualTo(DEFAULT_DISABILITY);
        assertThat(testStudent.getBpl()).isEqualTo(DEFAULT_BPL);
        assertThat(testStudent.getDisadvantage()).isEqualTo(DEFAULT_DISADVANTAGE);
        assertThat(testStudent.getTransport()).isEqualTo(DEFAULT_TRANSPORT);
        assertThat(testStudent.getEscort()).isEqualTo(DEFAULT_ESCORT);
        assertThat(testStudent.getHostel()).isEqualTo(DEFAULT_HOSTEL);
        assertThat(testStudent.getTraining()).isEqualTo(DEFAULT_TRAINING);
        assertThat(testStudent.getBook()).isEqualTo(DEFAULT_BOOK);
        assertThat(testStudent.getUniformsets()).isEqualTo(DEFAULT_UNIFORMSETS);
        assertThat(testStudent.getHomeless()).isEqualTo(DEFAULT_HOMELESS);
        assertThat(testStudent.getFreeEducation()).isEqualTo(DEFAULT_FREE_EDUCATION);
        assertThat(testStudent.getCwsn()).isEqualTo(DEFAULT_CWSN);
        assertThat(testStudent.getDisease()).isEqualTo(DEFAULT_DISEASE);
        assertThat(testStudent.getPrecautions()).isEqualTo(DEFAULT_PRECAUTIONS);
        assertThat(testStudent.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testStudent.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createStudentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // Create the Student with an existing ID
        student.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStudents() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList
        restStudentMockMvc.perform(get("/api/students?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].motherName").value(hasItem(DEFAULT_MOTHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].schoolName").value(hasItem(DEFAULT_SCHOOL_NAME.toString())))
            .andExpect(jsonPath("$.[*].admissionNumber").value(hasItem(DEFAULT_ADMISSION_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].rollNo").value(hasItem(DEFAULT_ROLL_NO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].profile").value(hasItem(DEFAULT_PROFILE.toString())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].locality").value(hasItem(DEFAULT_LOCALITY.toString())))
            .andExpect(jsonPath("$.[*].aadhar").value(hasItem(DEFAULT_AADHAR.toString())))
            .andExpect(jsonPath("$.[*].dateOfAdmission").value(hasItem(DEFAULT_DATE_OF_ADMISSION.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].previousClass").value(hasItem(DEFAULT_PREVIOUS_CLASS.toString())))
            .andExpect(jsonPath("$.[*].previousYear").value(hasItem(DEFAULT_PREVIOUS_YEAR.toString())))
            .andExpect(jsonPath("$.[*].disability").value(hasItem(DEFAULT_DISABILITY.toString())))
            .andExpect(jsonPath("$.[*].bpl").value(hasItem(DEFAULT_BPL.toString())))
            .andExpect(jsonPath("$.[*].disadvantage").value(hasItem(DEFAULT_DISADVANTAGE.toString())))
            .andExpect(jsonPath("$.[*].transport").value(hasItem(DEFAULT_TRANSPORT.toString())))
            .andExpect(jsonPath("$.[*].escort").value(hasItem(DEFAULT_ESCORT.toString())))
            .andExpect(jsonPath("$.[*].hostel").value(hasItem(DEFAULT_HOSTEL.toString())))
            .andExpect(jsonPath("$.[*].training").value(hasItem(DEFAULT_TRAINING.toString())))
            .andExpect(jsonPath("$.[*].book").value(hasItem(DEFAULT_BOOK.toString())))
            .andExpect(jsonPath("$.[*].uniformsets").value(hasItem(DEFAULT_UNIFORMSETS.toString())))
            .andExpect(jsonPath("$.[*].homeless").value(hasItem(DEFAULT_HOMELESS.toString())))
            .andExpect(jsonPath("$.[*].freeEducation").value(hasItem(DEFAULT_FREE_EDUCATION.toString())))
            .andExpect(jsonPath("$.[*].cwsn").value(hasItem(DEFAULT_CWSN.toString())))
            .andExpect(jsonPath("$.[*].disease").value(hasItem(DEFAULT_DISEASE.toString())))
            .andExpect(jsonPath("$.[*].precautions").value(hasItem(DEFAULT_PRECAUTIONS.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void getStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(student.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fatherName").value(DEFAULT_FATHER_NAME.toString()))
            .andExpect(jsonPath("$.motherName").value(DEFAULT_MOTHER_NAME.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.schoolName").value(DEFAULT_SCHOOL_NAME.toString()))
            .andExpect(jsonPath("$.admissionNumber").value(DEFAULT_ADMISSION_NUMBER.toString()))
            .andExpect(jsonPath("$.rollNo").value(DEFAULT_ROLL_NO))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.profile").value(DEFAULT_PROFILE.toString()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.locality").value(DEFAULT_LOCALITY.toString()))
            .andExpect(jsonPath("$.aadhar").value(DEFAULT_AADHAR.toString()))
            .andExpect(jsonPath("$.dateOfAdmission").value(DEFAULT_DATE_OF_ADMISSION.toString()))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION.toString()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.previousClass").value(DEFAULT_PREVIOUS_CLASS.toString()))
            .andExpect(jsonPath("$.previousYear").value(DEFAULT_PREVIOUS_YEAR.toString()))
            .andExpect(jsonPath("$.disability").value(DEFAULT_DISABILITY.toString()))
            .andExpect(jsonPath("$.bpl").value(DEFAULT_BPL.toString()))
            .andExpect(jsonPath("$.disadvantage").value(DEFAULT_DISADVANTAGE.toString()))
            .andExpect(jsonPath("$.transport").value(DEFAULT_TRANSPORT.toString()))
            .andExpect(jsonPath("$.escort").value(DEFAULT_ESCORT.toString()))
            .andExpect(jsonPath("$.hostel").value(DEFAULT_HOSTEL.toString()))
            .andExpect(jsonPath("$.training").value(DEFAULT_TRAINING.toString()))
            .andExpect(jsonPath("$.book").value(DEFAULT_BOOK.toString()))
            .andExpect(jsonPath("$.uniformsets").value(DEFAULT_UNIFORMSETS.toString()))
            .andExpect(jsonPath("$.homeless").value(DEFAULT_HOMELESS.toString()))
            .andExpect(jsonPath("$.freeEducation").value(DEFAULT_FREE_EDUCATION.toString()))
            .andExpect(jsonPath("$.cwsn").value(DEFAULT_CWSN.toString()))
            .andExpect(jsonPath("$.disease").value(DEFAULT_DISEASE.toString()))
            .andExpect(jsonPath("$.precautions").value(DEFAULT_PRECAUTIONS.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingStudent() throws Exception {
        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student
        Student updatedStudent = studentRepository.findOne(student.getId());
        // Disconnect from session so that the updates on updatedStudent are not directly saved in db
        em.detach(updatedStudent);
        updatedStudent
            .name(UPDATED_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .motherName(UPDATED_MOTHER_NAME)
            .dob(UPDATED_DOB)
            .schoolName(UPDATED_SCHOOL_NAME)
            .admissionNumber(UPDATED_ADMISSION_NUMBER)
            .rollNo(UPDATED_ROLL_NO)
            .email(UPDATED_EMAIL)
            .address(UPDATED_ADDRESS)
            .mobile(UPDATED_MOBILE)
            .phone(UPDATED_PHONE)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .profile(UPDATED_PROFILE)
            .image(UPDATED_IMAGE)
            .gender(UPDATED_GENDER)
            .locality(UPDATED_LOCALITY)
            .aadhar(UPDATED_AADHAR)
            .dateOfAdmission(UPDATED_DATE_OF_ADMISSION)
            .religion(UPDATED_RELIGION)
            .category(UPDATED_CATEGORY)
            .previousClass(UPDATED_PREVIOUS_CLASS)
            .previousYear(UPDATED_PREVIOUS_YEAR)
            .disability(UPDATED_DISABILITY)
            .bpl(UPDATED_BPL)
            .disadvantage(UPDATED_DISADVANTAGE)
            .transport(UPDATED_TRANSPORT)
            .escort(UPDATED_ESCORT)
            .hostel(UPDATED_HOSTEL)
            .training(UPDATED_TRAINING)
            .book(UPDATED_BOOK)
            .uniformsets(UPDATED_UNIFORMSETS)
            .homeless(UPDATED_HOMELESS)
            .freeEducation(UPDATED_FREE_EDUCATION)
            .cwsn(UPDATED_CWSN)
            .disease(UPDATED_DISEASE)
            .precautions(UPDATED_PRECAUTIONS)
            .active(UPDATED_ACTIVE)
            .isDeleted(UPDATED_IS_DELETED);

        restStudentMockMvc.perform(put("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStudent)))
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudent.getFatherName()).isEqualTo(UPDATED_FATHER_NAME);
        assertThat(testStudent.getMotherName()).isEqualTo(UPDATED_MOTHER_NAME);
        assertThat(testStudent.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testStudent.getSchoolName()).isEqualTo(UPDATED_SCHOOL_NAME);
        assertThat(testStudent.getAdmissionNumber()).isEqualTo(UPDATED_ADMISSION_NUMBER);
        assertThat(testStudent.getRollNo()).isEqualTo(UPDATED_ROLL_NO);
        assertThat(testStudent.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testStudent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStudent.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testStudent.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testStudent.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testStudent.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testStudent.getProfile()).isEqualTo(UPDATED_PROFILE);
        assertThat(testStudent.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testStudent.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testStudent.getLocality()).isEqualTo(UPDATED_LOCALITY);
        assertThat(testStudent.getAadhar()).isEqualTo(UPDATED_AADHAR);
        assertThat(testStudent.getDateOfAdmission()).isEqualTo(UPDATED_DATE_OF_ADMISSION);
        assertThat(testStudent.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testStudent.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testStudent.getPreviousClass()).isEqualTo(UPDATED_PREVIOUS_CLASS);
        assertThat(testStudent.getPreviousYear()).isEqualTo(UPDATED_PREVIOUS_YEAR);
        assertThat(testStudent.getDisability()).isEqualTo(UPDATED_DISABILITY);
        assertThat(testStudent.getBpl()).isEqualTo(UPDATED_BPL);
        assertThat(testStudent.getDisadvantage()).isEqualTo(UPDATED_DISADVANTAGE);
        assertThat(testStudent.getTransport()).isEqualTo(UPDATED_TRANSPORT);
        assertThat(testStudent.getEscort()).isEqualTo(UPDATED_ESCORT);
        assertThat(testStudent.getHostel()).isEqualTo(UPDATED_HOSTEL);
        assertThat(testStudent.getTraining()).isEqualTo(UPDATED_TRAINING);
        assertThat(testStudent.getBook()).isEqualTo(UPDATED_BOOK);
        assertThat(testStudent.getUniformsets()).isEqualTo(UPDATED_UNIFORMSETS);
        assertThat(testStudent.getHomeless()).isEqualTo(UPDATED_HOMELESS);
        assertThat(testStudent.getFreeEducation()).isEqualTo(UPDATED_FREE_EDUCATION);
        assertThat(testStudent.getCwsn()).isEqualTo(UPDATED_CWSN);
        assertThat(testStudent.getDisease()).isEqualTo(UPDATED_DISEASE);
        assertThat(testStudent.getPrecautions()).isEqualTo(UPDATED_PRECAUTIONS);
        assertThat(testStudent.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testStudent.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Create the Student

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStudentMockMvc.perform(put("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);
        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        // Get the student
        restStudentMockMvc.perform(delete("/api/students/{id}", student.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Student.class);
        Student student1 = new Student();
        student1.setId(1L);
        Student student2 = new Student();
        student2.setId(student1.getId());
        assertThat(student1).isEqualTo(student2);
        student2.setId(2L);
        assertThat(student1).isNotEqualTo(student2);
        student1.setId(null);
        assertThat(student1).isNotEqualTo(student2);
    }
}
