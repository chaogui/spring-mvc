package com.spring.mvc.controller;

import com.spring.mvc.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Value("${countries}")//will split the string countries in application.properties with comma
    private List<String> countries;

    @Value("${languages}")//will split the string countries in application.properties with comma
    private List<String> languages;

    @Value("${systems}")//will split the string countries in application.properties with comma
    private List<String> systems;

    @GetMapping("/show-student-form")
    public String showStudentForm(Model model){

        Student student = new Student();
        student.setFirstName("firstName");
        student.setLastName("lastName");
        model.addAttribute("student", student);
        //model.addAttribute("date", LocalDateTime.now());
        //model.addAttribute("date1", LocalDateTime.now());

        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        model.addAttribute("systems", systems);



        //wil return hello helloworld.html
        return "show-student-form";
    }

    @PostMapping("/student-register-form")
    public String showRegisterForm(@ModelAttribute("student") Student student){

        //student is auto generated with parameters passed in the form
        //by the thymleaf expressions: student, firstName, lastName

        System.out.println("student is: " + student.getFirstName() + " " + student.getLastName());

        //wil return student-confirmation.html
        return "student-confirmation";
    }

}
