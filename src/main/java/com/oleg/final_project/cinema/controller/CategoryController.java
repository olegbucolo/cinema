package com.oleg.final_project.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @GetMapping
    public String index(HttpServletRequest request, Model model){
        model.addAttribute("uri", request.getRequestURI());
        return "categories/index";
    }
}
