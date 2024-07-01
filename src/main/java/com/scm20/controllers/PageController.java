package com.scm20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page handler");
        model.addAttribute("name","Substring Technologies");
        model.addAttribute("source", "Spring"); 
        model.addAttribute("githubRepo","https://github.com/theprogrammingfreak?tab=repositories");
        return "home";
    }
}
