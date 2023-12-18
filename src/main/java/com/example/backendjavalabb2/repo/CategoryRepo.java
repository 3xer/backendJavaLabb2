package com.example.backendjavalabb2.repo;

import com.example.backendjavalabb2.Dto.CategoryDto;
import com.example.backendjavalabb2.Dto.ProjectionCategory;
import com.example.backendjavalabb2.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends ListCrudRepository<Category, Long>{
    List<Category> findAllProjectedBy();
    @EntityGraph(attributePaths = {"places"})
    @Query("""    
                Select c from Category c join c.locations pl where pl.id = :id and pl.visible = true
                """)/*optional?*/
    Optional<CategoryDto> findAllByProjectedBy(@Param("id")Long id);

    Optional<Category> findByName(String name);
    @Override
    Optional<Category> findById(Long id);

}
