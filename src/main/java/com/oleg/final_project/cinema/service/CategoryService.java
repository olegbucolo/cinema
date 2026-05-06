package com.oleg.final_project.cinema.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oleg.final_project.cinema.model.movie.Category;
import com.oleg.final_project.cinema.repository.CategoryRepository;

@Service
public class CategoryService {
 
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Integer integer){
        return categoryRepository.findById(integer);
    }

    public void deleteById(Integer integer){
        categoryRepository.deleteById(integer);
    }

    public void delete(Category category){
        categoryRepository.delete(category);
    }

}
