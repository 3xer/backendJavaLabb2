package com.example.backendjavalabb2.controller;


import com.example.backendjavalabb2.Dto.LocationDto;
import com.example.backendjavalabb2.entity.Location;
import com.example.backendjavalabb2.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/locations")
public class LocationController {
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    LocationService locationService;

    @GetMapping
    public List<Location> getAllLocationsFromUser(){
        return locationService.getUserLocations();
    }
    @PostMapping
    public ResponseEntity<Location> addLocation(@Valid @RequestBody LocationDto place){
        Location createdLocation = locationService.addLocation(place);
        URI xchangeThisName = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLocation.getId())
                .toUri();

        return ResponseEntity.created(xchangeThisName).body(createdLocation);
    }

}
