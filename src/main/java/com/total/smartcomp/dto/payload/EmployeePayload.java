package com.total.smartcomp.dto.payload;

import java.sql.Date;

public class EmployeePayload {

    private Integer employeeId;

    private String GGI;

    private String firstname;

    private String lastname;

    private Date hiringDate;

    public EmployeePayload() {
    }

    public EmployeePayload(final Integer employeeId, final String GGI, final String firstname, final String lastname, final Date hiringDate) {
        this.employeeId = employeeId;
        this.GGI = GGI;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hiringDate = hiringDate;
    }

    public EmployeePayload(final String GGI, final String firstname, final String lastname, final Date hiringDate) {
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
