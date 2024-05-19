package com.Book.security;

import com.Book.entity.Employee;
import com.Book.exception.ResourceNotFoundException;
import com.Book.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        Employee employee = this.employeeRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user", "email: " + username, 0));
        return employee;
    }

}
