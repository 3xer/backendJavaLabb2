package com.example.backendjavalabb2.Dto;

import com.example.backendjavalabb2.entity.Category;

import java.io.Serializable;

public record LocationDto(
        Long id,
        String name,
        Boolean visible,
        Float lat,
        Float lon,
        String category,
        String description) implements Serializable {

}
