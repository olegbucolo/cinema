package com.oleg.final_project.cinema.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oleg.final_project.cinema.model.movie.Category;
import com.oleg.final_project.cinema.service.CategoryService;

@RestController
@CrossOrigin(origins = "https://cinema-lhjy.onrender.com")
@RequestMapping("/api/categories")
public class RestCategoriesController {

    private final CategoryService categoryService;

    public RestCategoriesController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> index(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category show(@PathVariable Integer id){
        return categoryService.findById(id).orElseThrow();
    }
}
