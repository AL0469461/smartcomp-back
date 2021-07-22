package com.total.smartcomp;

import com.total.smartcomp.entity.Employee;
import com.total.smartcomp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    void findByFirstname() {
        final Optional<Employee> employee = repository.findByFirstname("demo-test");
        assertFalse(employee.isPresent());
    }
}