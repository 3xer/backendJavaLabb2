package com.example.backendjavalabb2.controller;


import com.example.backendjavalabb2.Dto.ProjectionCategory;
import com.example.backendjavalabb2.exceptions.ResourceNotFoundException;
import com.example.backendjavalabb2.entity.Category;
import com.example.backendjavalabb2.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories"
        )

public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }
    @GetMapping("/{id:\\d+}")
        public Category getCategoryById(@PathVariable long id){
            return categoryService.getOne(id).orElseThrow(() -> new ResourceNotFoundException("category not found"));
        }

    @GetMapping("/{name:.*\\D.*}")
    public Category getOneById(@PathVariable String name){
            return categoryService.getOne(name).orElseThrow(() -> new ResourceNotFoundException("category not found"));
    }
    @PostMapping
    public ResponseEntity<Category> add(@Valid @RequestBody Category category){
    Category createdCategory = categoryService.create(category);

    URI place = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdCategory.getId())
            .toUri();

    return ResponseEntity.created(place).body(createdCategory);
    }
}
