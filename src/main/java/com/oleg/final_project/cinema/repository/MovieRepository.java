package com.oleg.final_project.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oleg.final_project.cinema.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
    
}
