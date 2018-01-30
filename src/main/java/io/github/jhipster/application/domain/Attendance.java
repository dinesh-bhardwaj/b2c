package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.MonthFields;

import io.github.jhipster.application.domain.enumeration.AttendanceField;

/**
 * A Attendance.
 */
@Entity
@Table(name = "attendance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "month")
    private MonthFields month;

    @Column(name = "jhi_date")
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(name = "attendance")
    private AttendanceField attendance;

    @ManyToOne
    private Student student;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MonthFields getMonth() {
        return month;
    }

    public Attendance month(MonthFields month) {
        this.month = month;
        return this;
    }

    public void setMonth(MonthFields month) {
        this.month = month;
    }

    public Instant getDate() {
        return date;
    }

    public Attendance date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public AttendanceField getAttendance() {
        return attendance;
    }

    public Attendance attendance(AttendanceField attendance) {
        this.attendance = attendance;
        return this;
    }

    public void setAttendance(AttendanceField attendance) {
        this.attendance = attendance;
    }

    public Student getStudent() {
        return student;
    }

    public Attendance student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        Attendance attendance = (Attendance) o;
        if (attendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Attendance{" +
            "id=" + getId() +
            ", month='" + getMonth() + "'" +
            ", date='" + getDate() + "'" +
            ", attendance='" + getAttendance() + "'" +
            "}";
    }
}
