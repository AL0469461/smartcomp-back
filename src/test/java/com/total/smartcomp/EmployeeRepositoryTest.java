package com.total.smartcomp;

import com.total.smartcomp.model.Employee;
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
    void findByName() {
        final Optional<Employee> employee = repository.findByName("demo-test");

        assertFalse(employee.isPresent());
    }
}