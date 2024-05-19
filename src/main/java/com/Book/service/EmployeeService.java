package com.Book.service;

import com.Book.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    public String insert(Employee employee);
    public Employee getEmployeeById(Integer id);
    public List<Employee>getAllEmployee();
    void deleteEmployee(Integer id);

    Employee registerNewEmployee(Employee employee);

}
