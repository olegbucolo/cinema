package com.oleg.final_project.cinema.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.oleg.final_project.cinema.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
