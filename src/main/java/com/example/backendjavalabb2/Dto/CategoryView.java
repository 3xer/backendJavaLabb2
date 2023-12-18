package com.example.backendjavalabb2.Dto;

import java.time.LocalDateTime;
import java.util.List;

public interface CategoryView {
    String getName();
    String getSymbol();
    List<LocationView> getLocations();

    interface  LocationView {
        String getName();
        String getDescription();
        LocalDateTime getLastModification();
    }
}
