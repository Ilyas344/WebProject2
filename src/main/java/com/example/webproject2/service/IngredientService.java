package com.example.webproject2.service;

import com.example.webproject2.model.Ingredient;

import java.util.Optional;

public interface IngredientService {
    Ingredient add(Ingredient ingredient);
    Optional<Ingredient> getId(Long id);
}
