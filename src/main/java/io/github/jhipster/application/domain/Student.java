package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.ProfileSelection;

import io.github.jhipster.application.domain.enumeration.GenderSelection;

import io.github.jhipster.application.domain.enumeration.ReligionField;

import io.github.jhipster.application.domain.enumeration.CategoryField;

import io.github.jhipster.application.domain.enumeration.PreviousClassField;

import io.github.jhipster.application.domain.enumeration.Previousyear;

import io.github.jhipster.application.domain.enumeration.Disability;

import io.github.jhipster.application.domain.enumeration.YESNOSELECTION;

import io.github.jhipster.application.domain.enumeration.NAYESNOSELECTION;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "dob")
    private Instant dob;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "admission_number")
    private String admissionNumber;

    @Column(name = "roll_no")
    private Integer rollNo;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String username;

    @Column(name = "jhi_password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_profile")
    private ProfileSelection profile;

    @Column(name = "image")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderSelection gender;

    @Column(name = "locality")
    private String locality;

    @Column(name = "aadhar")
    private String aadhar;

    @Column(name = "date_of_admission")
    private Instant dateOfAdmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "religion")
    private ReligionField religion;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private CategoryField category;

    @Enumerated(EnumType.STRING)
    @Column(name = "previous_class")
    private PreviousClassField previousClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "previous_year")
    private Previousyear previousYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "disability")
    private Disability disability;

    @Enumerated(EnumType.STRING)
    @Column(name = "bpl")
    private YESNOSELECTION bpl;

    @Enumerated(EnumType.STRING)
    @Column(name = "disadvantage")
    private NAYESNOSELECTION disadvantage;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport")
    private NAYESNOSELECTION transport;

    @Enumerated(EnumType.STRING)
    @Column(name = "escort")
    private NAYESNOSELECTION escort;

    @Enumerated(EnumType.STRING)
    @Column(name = "hostel")
    private NAYESNOSELECTION hostel;

    @Enumerated(EnumType.STRING)
    @Column(name = "training")
    private NAYESNOSELECTION training;

    @Enumerated(EnumType.STRING)
    @Column(name = "book")
    private NAYESNOSELECTION book;

    @Enumerated(EnumType.STRING)
    @Column(name = "uniformsets")
    private NAYESNOSELECTION uniformsets;

    @Enumerated(EnumType.STRING)
    @Column(name = "homeless")
    private NAYESNOSELECTION homeless;

    @Enumerated(EnumType.STRING)
    @Column(name = "free_education")
    private NAYESNOSELECTION freeEducation;

    @Enumerated(EnumType.STRING)
    @Column(name = "cwsn")
    private NAYESNOSELECTION cwsn;

    @Column(name = "disease")
    private String disease;

    @Column(name = "precautions")
    private String precautions;

    @Column(name = "active")
    private Instant active;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToOne
    @JoinColumn(unique = true)
    private User userId;

    @ManyToOne
    private SchoolClass schoolClass;

    @ManyToOne
    private Section section;

    @ManyToOne
    private Session session;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Student fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public Student motherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Instant getDob() {
        return dob;
    }

    public Student dob(Instant dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Student schoolName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public Student admissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
        return this;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public Student rollNo(Integer rollNo) {
        this.rollNo = rollNo;
        return this;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getEmail() {
        return email;
    }

    public Student email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public Student address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public Student mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public Student phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public Student username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Student password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProfileSelection getProfile() {
        return profile;
    }

    public Student profile(ProfileSelection profile) {
        this.profile = profile;
        return this;
    }

    public void setProfile(ProfileSelection profile) {
        this.profile = profile;
    }

    public String getImage() {
        return image;
    }

    public Student image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public GenderSelection getGender() {
        return gender;
    }

    public Student gender(GenderSelection gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(GenderSelection gender) {
        this.gender = gender;
    }

    public String getLocality() {
        return locality;
    }

    public Student locality(String locality) {
        this.locality = locality;
        return this;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAadhar() {
        return aadhar;
    }

    public Student aadhar(String aadhar) {
        this.aadhar = aadhar;
        return this;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public Instant getDateOfAdmission() {
        return dateOfAdmission;
    }

    public Student dateOfAdmission(Instant dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
        return this;
    }

    public void setDateOfAdmission(Instant dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public ReligionField getReligion() {
        return religion;
    }

    public Student religion(ReligionField religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(ReligionField religion) {
        this.religion = religion;
    }

    public CategoryField getCategory() {
        return category;
    }

    public Student category(CategoryField category) {
        this.category = category;
        return this;
    }

    public void setCategory(CategoryField category) {
        this.category = category;
    }

    public PreviousClassField getPreviousClass() {
        return previousClass;
    }

    public Student previousClass(PreviousClassField previousClass) {
        this.previousClass = previousClass;
        return this;
    }

    public void setPreviousClass(PreviousClassField previousClass) {
        this.previousClass = previousClass;
    }

    public Previousyear getPreviousYear() {
        return previousYear;
    }

    public Student previousYear(Previousyear previousYear) {
        this.previousYear = previousYear;
        return this;
    }

    public void setPreviousYear(Previousyear previousYear) {
        this.previousYear = previousYear;
    }

    public Disability getDisability() {
        return disability;
    }

    public Student disability(Disability disability) {
        this.disability = disability;
        return this;
    }

    public void setDisability(Disability disability) {
        this.disability = disability;
    }

    public YESNOSELECTION getBpl() {
        return bpl;
    }

    public Student bpl(YESNOSELECTION bpl) {
        this.bpl = bpl;
        return this;
    }

    public void setBpl(YESNOSELECTION bpl) {
        this.bpl = bpl;
    }

    public NAYESNOSELECTION getDisadvantage() {
        return disadvantage;
    }

    public Student disadvantage(NAYESNOSELECTION disadvantage) {
        this.disadvantage = disadvantage;
        return this;
    }

    public void setDisadvantage(NAYESNOSELECTION disadvantage) {
        this.disadvantage = disadvantage;
    }

    public NAYESNOSELECTION getTransport() {
        return transport;
    }

    public Student transport(NAYESNOSELECTION transport) {
        this.transport = transport;
        return this;
    }

    public void setTransport(NAYESNOSELECTION transport) {
        this.transport = transport;
    }

    public NAYESNOSELECTION getEscort() {
        return escort;
    }

    public Student escort(NAYESNOSELECTION escort) {
        this.escort = escort;
        return this;
    }

    public void setEscort(NAYESNOSELECTION escort) {
        this.escort = escort;
    }

    public NAYESNOSELECTION getHostel() {
        return hostel;
    }

    public Student hostel(NAYESNOSELECTION hostel) {
        this.hostel = hostel;
        return this;
    }

    public void setHostel(NAYESNOSELECTION hostel) {
        this.hostel = hostel;
    }

    public NAYESNOSELECTION getTraining() {
        return training;
    }

    public Student training(NAYESNOSELECTION training) {
        this.training = training;
        return this;
    }

    public void setTraining(NAYESNOSELECTION training) {
        this.training = training;
    }

    public NAYESNOSELECTION getBook() {
        return book;
    }

    public Student book(NAYESNOSELECTION book) {
        this.book = book;
        return this;
    }

    public void setBook(NAYESNOSELECTION book) {
        this.book = book;
    }

    public NAYESNOSELECTION getUniformsets() {
        return uniformsets;
    }

    public Student uniformsets(NAYESNOSELECTION uniformsets) {
        this.uniformsets = uniformsets;
        return this;
    }

    public void setUniformsets(NAYESNOSELECTION uniformsets) {
        this.uniformsets = uniformsets;
    }

    public NAYESNOSELECTION getHomeless() {
        return homeless;
    }

    public Student homeless(NAYESNOSELECTION homeless) {
        this.homeless = homeless;
        return this;
    }

    public void setHomeless(NAYESNOSELECTION homeless) {
        this.homeless = homeless;
    }

    public NAYESNOSELECTION getFreeEducation() {
        return freeEducation;
    }

    public Student freeEducation(NAYESNOSELECTION freeEducation) {
        this.freeEducation = freeEducation;
        return this;
    }

    public void setFreeEducation(NAYESNOSELECTION freeEducation) {
        this.freeEducation = freeEducation;
    }

    public NAYESNOSELECTION getCwsn() {
        return cwsn;
    }

    public Student cwsn(NAYESNOSELECTION cwsn) {
        this.cwsn = cwsn;
        return this;
    }

    public void setCwsn(NAYESNOSELECTION cwsn) {
        this.cwsn = cwsn;
    }

    public String getDisease() {
        return disease;
    }

    public Student disease(String disease) {
        this.disease = disease;
        return this;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getPrecautions() {
        return precautions;
    }

    public Student precautions(String precautions) {
        this.precautions = precautions;
        return this;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    public Instant getActive() {
        return active;
    }

    public Student active(Instant active) {
        this.active = active;
        return this;
    }

    public void setActive(Instant active) {
        this.active = active;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public Student isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public User getUserId() {
        return userId;
    }

    public Student userId(User user) {
        this.userId = user;
        return this;
    }

    public void setUserId(User user) {
        this.userId = user;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public Student schoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Section getSection() {
        return section;
    }

    public Student section(Section section) {
        this.section = section;
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Session getSession() {
        return session;
    }

    public Student session(Session session) {
        this.session = session;
        return this;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", motherName='" + getMotherName() + "'" +
            ", dob='" + getDob() + "'" +
            ", schoolName='" + getSchoolName() + "'" +
            ", admissionNumber='" + getAdmissionNumber() + "'" +
            ", rollNo=" + getRollNo() +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", phone='" + getPhone() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", profile='" + getProfile() + "'" +
            ", image='" + getImage() + "'" +
            ", gender='" + getGender() + "'" +
            ", locality='" + getLocality() + "'" +
            ", aadhar='" + getAadhar() + "'" +
            ", dateOfAdmission='" + getDateOfAdmission() + "'" +
            ", religion='" + getReligion() + "'" +
            ", category='" + getCategory() + "'" +
            ", previousClass='" + getPreviousClass() + "'" +
            ", previousYear='" + getPreviousYear() + "'" +
            ", disability='" + getDisability() + "'" +
            ", bpl='" + getBpl() + "'" +
            ", disadvantage='" + getDisadvantage() + "'" +
            ", transport='" + getTransport() + "'" +
            ", escort='" + getEscort() + "'" +
            ", hostel='" + getHostel() + "'" +
            ", training='" + getTraining() + "'" +
            ", book='" + getBook() + "'" +
            ", uniformsets='" + getUniformsets() + "'" +
            ", homeless='" + getHomeless() + "'" +
            ", freeEducation='" + getFreeEducation() + "'" +
            ", cwsn='" + getCwsn() + "'" +
            ", disease='" + getDisease() + "'" +
            ", precautions='" + getPrecautions() + "'" +
            ", active='" + getActive() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
