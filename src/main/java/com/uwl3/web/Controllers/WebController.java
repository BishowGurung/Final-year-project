package com.uwl3.web.Controllers;

import com.uwl3.Encryption.EncryptionService;
import com.uwl3.domain.dao.Employee;
import com.uwl3.domain.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@Controller
@RestController
public class WebController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EncryptionService encryptionService;

    @RequestMapping("")
    public  String index(){
        return "index";
    }

    @GetMapping(value = "/createUser")
    public String createUser(String username,String password){
        var employee = Employee.builder().employeeId((int)(Math.random() * (100 - 1)) + 1)
                .username(username).password(encryptionService.encodeValue(password)).build();
        employeeRepository.save(employee);
        return "OK";
    }

    @GetMapping("/authenticateUser")
    public String authenticateUser(String username,String password) throws UnsupportedEncodingException {
        if(!employeeRepository.findByUsername(username).isEmpty()){
            Employee employee = employeeRepository.findByUsername(username).get();
            System.out.println(employee.getPassword() + ":pAAS");
            System.out.println(encryptionService.decodeValue(employee.getPassword()) + ":pAAS");
            if (employee.getUsername().equals(username) && encryptionService.decodeValue(employee.getPassword())
                    .equals(password)){
                return "OK";
            }else {
                return "Authentication Failed";
            }
        }
        return "No user found";
    }

}
