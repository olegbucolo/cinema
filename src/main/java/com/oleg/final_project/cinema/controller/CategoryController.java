package com.oleg.final_project.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oleg.final_project.cinema.model.movie.Category;
import com.oleg.final_project.cinema.model.movie.Movie;
import com.oleg.final_project.cinema.service.CategoryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("uri", request.getRequestURI());
        return "categories/index";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("category", new Category());
        return "categories/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("uri", request.getRequestURI());
            return "categories/create";
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/update")
    public String update(Model model, HttpServletRequest request, @PathVariable Integer id) {
        model.addAttribute("category", categoryService.findById(id).orElseThrow());
        model.addAttribute("uri", request.getRequestURI());
        return "categories/update";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid @ModelAttribute Category category, BindingResult bindingResult, @PathVariable Integer id, HttpServletRequest request, Model model) {
        if(bindingResult.hasErrors()){
            // model.addAttribute("category", categoryService.findById(id).orElseThrow());
            model.addAttribute("uri", request.getRequestURI());
            return "categories/update";
        }
        Category categoryDB = categoryService.findById(id).orElseThrow();
        categoryDB.setTitle(category.getTitle());
        categoryDB.setDescription(category.getDescription());
        categoryDB.setId(category.getId());
        categoryService.save(categoryDB);
        return "redirect:/categories";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        Category category = categoryService.findById(id).orElseThrow();

        for (Movie movie : category.getMovies()) {
            movie.getCategories().remove(category);
        }

        categoryService.delete(category);
        return "redirect:/categories";
    }

}
