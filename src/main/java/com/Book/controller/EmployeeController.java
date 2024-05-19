package com.Book.controller;

import com.Book.ApiResponse;
import com.Book.entity.Employee;
import com.Book.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<Employee> registerUser(@RequestBody Employee employee) {
        Employee employee1 = this.employeeService.registerNewEmployee(employee);
        return new ResponseEntity<Employee>(employee1, HttpStatus.CREATED);
    }

//    @PostMapping("/addemployee")
//    public ResponseEntity<String>inserEmployee(@RequestBody Employee employee){
//        String insert = employeeService.insert(employee);
//        return new ResponseEntity<>(insert, HttpStatus.CREATED);
//    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee>getBook(@PathVariable Integer id){
        Employee employeeById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeById,HttpStatus.OK);
    }
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>>getAllEmployee(){
        List<Employee> allEmployee = employeeService.getAllEmployee();
        return new ResponseEntity<>(allEmployee,HttpStatus.OK);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<String>updateEmployee(@RequestBody Employee employee){
        String insert = employeeService.insert(employee);
        return  new ResponseEntity<>(insert,HttpStatus.OK);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        this.employeeService.deleteEmployee(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);
    }

}
