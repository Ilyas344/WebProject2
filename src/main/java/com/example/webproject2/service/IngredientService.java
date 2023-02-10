package com.example.webproject2.service;

import com.example.webproject2.model.Ingredient;
import com.example.webproject2.model.Recipe;

import java.util.Map;
import java.util.Optional;

public interface IngredientService {
    Ingredient add(Ingredient ingredient);

    Optional<Ingredient> getId(Long id);

    Ingredient update(Long id, Ingredient ingredient);

    Ingredient delete(Long id);

    Map<Long, Ingredient> getAll();
}
