package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class DemoController {

    @RequestMapping("/hello")
    public String sayhello(Model model){

        model.addAttribute("date", LocalDateTime.now());
        model.addAttribute("date1", LocalDateTime.now());

        //wil return hello helloworld.html
        return "helloworld";
    }

    @RequestMapping("/hello2")
    public String sayhello2(Model model){

        model.addAttribute("date", LocalDateTime.now());

        //wil return hello helloworld.html
        return "helloworld1";
    }

    @RequestMapping("/test-mav")
    public ModelAndView testMav(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("date", LocalDateTime.now());
        mav.setViewName("helloworld");

        return mav;
    }
}
