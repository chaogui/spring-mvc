package com.spring.mvc.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class HelloWorldController {

    @RequestMapping("/show-form")
    public String showForm(){

        //model.addAttribute("date", LocalDateTime.now());
        //model.addAttribute("date1", LocalDateTime.now());

        //wil return hello helloworld.html
        return "show-form";
    }

    @RequestMapping("/process-form")
    public String processForm(){

       // model.addAttribute("date", LocalDateTime.now());

        //wil return hello helloworld.html
        return "process-form";
    }

    @RequestMapping("/process-form-2")
    public String processForm2(HttpServletRequest request, Model model){

        String studentName = request.getParameter("studentName");

        model.addAttribute("message", "Welcome " + studentName.toUpperCase());
        // model.addAttribute("date", LocalDateTime.now());

        //wil return hello helloworld.html
        return "process-form-2";
    }

    //@RequestMapping(path = "/process-form-3", method = RequestMethod.GET)
    @GetMapping(path = "/process-form-3")
    public String processForm3(@RequestParam("studentName") String studentName, Model model){
        // comparing with processForm2, the line to read param is commented
        //String studentName = request.getParameter("studentName");

        model.addAttribute("message", "Welcome " + studentName.toUpperCase());

        //wil return process-form-2.html
        return "process-form-2";
    }

    //@RequestMapping(path = "/process-form-3", method = RequestMethod.POST)
    @PostMapping(path = "/process-post-form")
    public String processPostForm(@RequestParam("studentName") String studentName, Model model){
        // comparing with processForm2, the line to read param is commented
        //String studentName = request.getParameter("studentName");

        model.addAttribute("message", "Welcome " + studentName.toUpperCase());

        //wil return process-form-2.html
        return "process-post-form";
    }
}
