package com.oleg.final_project.cinema.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oleg.final_project.cinema.model.movie.Movie;
import com.oleg.final_project.cinema.service.MovieService;

@RestController
@CrossOrigin(origins = "https://cinema-frontend-seven.vercel.app/")
@RequestMapping("/api/movies")
public class RestMovieController {
    private final MovieService movieService;

    public RestMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> index() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie show(@PathVariable Integer id){
        return movieService.findById(id).orElseThrow();
    }
}
