package com.total.smartcomp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Employee {
    @Id
    private String id;
    private String name;
    private String firstname;
    private String profile;
}
