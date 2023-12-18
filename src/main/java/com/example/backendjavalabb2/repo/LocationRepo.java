package com.example.backendjavalabb2.repo;

import com.example.backendjavalabb2.Dto.LocationDto;
import com.example.backendjavalabb2.entity.Location;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface LocationRepo extends ListCrudRepository<Location,Long> {
    List<Location> findByUserName(String userName);

}
