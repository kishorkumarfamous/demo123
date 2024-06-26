package com.Book.repo;

import com.Book.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Serializable> {
    Optional<Employee> findByEmail(String email);
}
