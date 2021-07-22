package com.total.smartcomp.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(name = "GGI")
    private String GGI;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "hiring_date")
    private Date hiringDate;

    public Employee() {
    }

    public Employee(final Integer employeeId, final String GGI, final String firstname, final String lastname, final Date hiringDate) {
        this.employeeId = employeeId;
        this.GGI = GGI;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hiringDate = hiringDate;
    }

    public Employee(final String GGI, final String firstname, final String lastname, final Date hiringDate) {
        employeeId = employeeId;
        this.GGI = GGI;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hiringDate = hiringDate;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getGGI() {
        return GGI;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setGGI(final String GGI) {
        this.GGI = GGI;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public void setHiringDate(final Date hiringDate) {
        this.hiringDate = hiringDate;
    }

}
