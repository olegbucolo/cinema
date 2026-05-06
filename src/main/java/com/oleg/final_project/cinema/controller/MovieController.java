package com.oleg.final_project.cinema.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.oleg.final_project.cinema.model.movie.Category;
import com.oleg.final_project.cinema.model.movie.Movie;
import com.oleg.final_project.cinema.service.CategoryService;
import com.oleg.final_project.cinema.service.MovieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;

    public MovieController(MovieService movieService, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.movieService = movieService;
    }

    @GetMapping
    public String index(HttpServletRequest request, Model model) {
        // giving the uri for button animations based on current page
        model.addAttribute("uri", request.getRequestURI());
        // giving the thymeleaf the list of movies
        model.addAttribute("movies", movieService.findAll());
        return "movies/index";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("movie", new Movie());
        model.addAttribute("categories", categoryService.findAll());
        return "movies/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Movie movie, BindingResult bindingResult,
            @RequestParam(required = false) List<Integer> categoryIds, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("uri", request.getRequestURI());
            return "movies/create";
        }

        Set<Category> categories = new HashSet<>();

        if (categoryIds != null) {
            for (Integer id : categoryIds) {
                categoryService.findById(id).ifPresent(categories::add);
            }
        }

        movie.setCategories(categories);
        movieService.save(movie);

        return "redirect:/movies";
    }

    @GetMapping("/{id}/update")
    public String update(Model model, HttpServletRequest request, @PathVariable Integer id) {
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("movie", movieService.findById(id).orElseThrow());
        model.addAttribute("categories", categoryService.findAll());
        return "movies/update";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid @ModelAttribute Movie movie, BindingResult bindingResult, @PathVariable Integer id,
            @RequestParam(required = false) List<Integer> categoryIds, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("uri", request.getRequestURI());
            model.addAttribute("categories", categoryService.findAll());
            return "movies/update";
        }

        Movie movieFromDB = movieService.findById(id).orElseThrow();
        Set<Category> categories = new HashSet<>();

        if (categoryIds != null) {
            for (Integer categoryId : categoryIds) {
                categories.add(categoryService.findById(categoryId).orElseThrow());
            }
        }
        movieFromDB.setDescription(movie.getDescription());
        movieFromDB.setTitle(movie.getTitle());
        movieFromDB.setPrice(movie.getPrice());
        movieFromDB.setRating(movie.getRating());
        movieFromDB.setCategories(categories);
        movieService.save(movieFromDB);
        return "redirect:/movies";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        movieService.deleteById(id);
        return "redirect:/movies";
    }
}
