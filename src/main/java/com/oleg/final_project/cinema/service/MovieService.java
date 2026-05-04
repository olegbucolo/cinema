package com.oleg.final_project.cinema.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oleg.final_project.cinema.model.Movie;
import com.oleg.final_project.cinema.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Integer integer) {
        return movieRepository.findById(integer);
    }

    public void deleteById(Integer integer) {
        movieRepository.deleteById(integer);
    }

}
