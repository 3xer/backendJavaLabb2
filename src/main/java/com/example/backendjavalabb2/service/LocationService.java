package com.example.backendjavalabb2.service;

import com.example.backendjavalabb2.Dto.LocationDto;
import com.example.backendjavalabb2.entity.Category;
import com.example.backendjavalabb2.entity.Location;
import com.example.backendjavalabb2.repo.LocationRepo;
import jakarta.transaction.Transactional;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Service
public class LocationService {
    public LocationService(LocationRepo placeRepository, CategoryService categoryService) {
        this.placeRepository = placeRepository;
        this.categoryService = categoryService;
    }

    LocationRepo placeRepository;
    CategoryService categoryService;
    @Transactional
    public Location addLocation(LocationDto location){
        if (location.lat() < -90 || location.lat() >90 || location.lon() < -180 || location.lon() > 180) {
            throw new IllegalArgumentException("Illegal latitude and/or longitude");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Category category = categoryService.getOne(location.category()).orElseThrow();
        var geo = Geometries.mkPoint(new G2D(location.lon(), location.lat()), WGS84);

        Location locationEntity = new Location();
        locationEntity.setName(location.name());
        locationEntity.setUserName(userName);
        locationEntity.setVisible(location.visible());
        locationEntity.setCoordinate(geo);
        category.addLocation(locationEntity);

        return placeRepository.save(locationEntity);
    }

    public List<Location> getUserLocations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return placeRepository.findByUserName(userName);

    }

}
