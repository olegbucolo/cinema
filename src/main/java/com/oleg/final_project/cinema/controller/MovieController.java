package com.oleg.final_project.cinema.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oleg.final_project.cinema.service.MovieService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
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
}
