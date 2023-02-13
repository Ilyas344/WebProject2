package com.example.webproject2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private int cookTime;
    private List<Ingredient> ingredients;
    private List<String> steps;


}
