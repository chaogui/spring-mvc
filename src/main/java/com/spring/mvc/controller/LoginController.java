package com.spring.mvc.controller;

import com.spring.mvc.entity.Employee;
import com.spring.mvc.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

   // login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/access-denied")
    public String denyAccess(){
        return "access-denied";
    }


}
