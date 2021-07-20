package com.total.smartcomp;

import com.total.smartcomp.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String> {
    Optional<Employee> findByName(String name);
}
