package com.oleg.final_project.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MovieController {
        
    @GetMapping
    public String index(){
        return "home/index";
    }
}
