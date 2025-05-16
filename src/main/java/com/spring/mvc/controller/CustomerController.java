package com.spring.mvc.controller;

import com.spring.mvc.model.Customer;
import com.spring.mvc.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

//    @Value("${countries}")//will split the string countries in application.properties with comma
//    private List<String> countries;
//
//    @Value("${languages}")//will split the string countries in application.properties with comma
//    private List<String> languages;
//
//    @Value("${systems}")//will split the string countries in application.properties with comma
//    private List<String> systems;

    @GetMapping("/")
    public String showForm(Model model){

        Customer customer = new Customer();

        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        model.addAttribute("customer", customer);
        //model.addAttribute("date", LocalDateTime.now());
        //model.addAttribute("date1", LocalDateTime.now());

        return "customer-form";
    }



    @PostMapping("/register-form")
    public String register(@Valid @ModelAttribute("customer") Customer customer, BindingResult result){

        //student is auto generated with parameters passed in the form
        //by the thymleaf expressions: student, firstName, lastName

        if(result.hasErrors()){
            return "customer-form";
        }
        //wil return student-confirmation.html
        return "customer-confirmation";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, editor);
    }

}
