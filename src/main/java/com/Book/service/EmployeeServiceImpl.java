package com.Book.service;

import com.Book.entity.Employee;
import com.Book.exception.ResourceNotFoundException;
import com.Book.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String insert(Employee employee) {
        employeeRepo.save(employee);
        return "success";
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

       return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public void deleteEmployee(Integer id) {
        this.employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
            this.employeeRepo.deleteById(id);
    }

    @Override
    public Employee registerNewEmployee(Employee employee) {

        employee.setPassword(this.passwordEncoder.encode(employee.getPassword()));
        Employee employee1 = this.employeeRepo.save(employee);
        return employee1;
    }

//    public UserDto registerNewUser(UserDto userDto) {
//        User user = this.modelMapper.map(userDto, User.class);
//        //encode the password
//        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
//        //set the roles new user
//        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
//        user.getRoles().add(role);
//        User newUser = this.userRepo.save(user);
//        return this.modelMapper.map(newUser, UserDto.class);
//    }

}
