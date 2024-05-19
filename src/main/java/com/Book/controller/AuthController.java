package com.Book.controller;



import com.Book.entity.Employee;
import com.Book.exception.ApiException;
import com.Book.security.CustomUserDetailService;
import com.Book.security.JwtAuthRequest;
import com.Book.security.JwtAuthResponse;
import com.Book.security.JwtTokenHelper;
import com.Book.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Qualifier("authenticationManager")
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
        private EmployeeService employeeService;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse>createToken(
            @RequestBody JwtAuthRequest request) throws Exception {
            this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());

        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username,String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);

        try {
            this.authenticationManager.authenticate(authenticationToken);

        }catch(BadCredentialsException e){
            System.out.println("invalid details!!!");
            throw new ApiException("invalid username or password!!");
        }


    }
    //register new user api
    @PostMapping("/register")
    public ResponseEntity<Employee> registerUser(@RequestBody Employee employee){
        Employee employee1 = this.employeeService.registerNewEmployee(employee);
        return new ResponseEntity<Employee>(employee1,HttpStatus.CREATED);
    }

}
