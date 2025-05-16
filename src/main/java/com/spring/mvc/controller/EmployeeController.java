package com.spring.mvc.controller;

import com.spring.mvc.entity.Employee;
import com.spring.mvc.model.Student;
import com.spring.mvc.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

   private final EmployeeService service;


   // list
    @GetMapping("/")
    public String getEmployees(Model model){
        List<Employee> employees = service.findAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    // list
    @GetMapping("/add-employee")
    public String addEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/add-employee-form";
    }

    @GetMapping("/update-employee")
    public String updateEmployee(@RequestParam("employeeId") Integer id, Model model){
        Employee employee = service.findById(id);
        model.addAttribute("employee", employee);
        return "employees/update-employee-form";
    }

    @GetMapping("/delete-employee")
    public String deleteEmployee(@RequestParam("employeeId") Integer id){

        service.deleteById(id);
        return "redirect:/employees/";
    }

    // get

    //put
    // list
    @PostMapping("/")
    public String saveEmployee(@ModelAttribute("Employee") Employee employee){
        service.save(employee);
        return "redirect:/employees/";
    }
//
//    @PutMapping("/")
//    public String updateEmployee(@ModelAttribute("Employee") Employee employee){
//        service.save(employee);
//        return "redirect:/employees/";
//    }

    // delete

}
