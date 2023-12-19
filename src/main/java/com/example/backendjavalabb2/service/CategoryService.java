package com.example.backendjavalabb2.service;


import com.example.backendjavalabb2.entity.Category;
import com.example.backendjavalabb2.repo.CategoryRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepo repository;
    public CategoryService(CategoryRepo repository){
        this.repository = repository;
    }
    public List<Category> getAll() {
        return repository.findAllProjectedBy();
    }
    public Category create(Category category) {
        return repository.save(category);
    }

    public Optional<Category> getOne(long id){
        return repository.findById(id);
    }
    public Optional<Category> getOne(String name) {
        return repository.findByName(name);
    }
}
