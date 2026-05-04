package com.oleg.final_project.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oleg.final_project.cinema.model.Category;
import com.oleg.final_project.cinema.service.CategoryService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(HttpServletRequest request, Model model){
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("uri", request.getRequestURI());
        return "categories/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("category", new Category());
        return "string";
    }
}
