package com.example.backendjavalabb2.entity;

import com.example.backendjavalabb2.Emojis.EmojiSymbol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @EmojiSymbol
    private String symbol;
    private String description;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Location> locations = new ArrayList<>();

    public void addLocation(Location location){
        locations.add(location);
        location.setCategory(this);
    }
    public void removeLocation(Location location){
        locations.remove(location);
        location.setCategory(null);
    }
}
