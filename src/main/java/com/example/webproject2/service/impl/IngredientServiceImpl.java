package com.example.webproject2.service.impl;

import com.example.webproject2.model.Ingredient;
import com.example.webproject2.model.Recipe;
import com.example.webproject2.service.IngredientService;
import com.example.webproject2.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static long idIngredient = 1;
    private final Map<Long, Ingredient> ingredients = new LinkedHashMap<>();
    private final ValidationService validationService;

    public IngredientServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        if (!validationService.validateIngredient(ingredient)) {
            throw new RuntimeException(ingredient.toString());
        }
        return ingredients.put(idIngredient++, ingredient);
    }

    @Override
    public Optional<Ingredient> getId(Long id) {
        return Optional.ofNullable(ingredients.get(id));
    }

    @Override
    public Ingredient update(Long id, Ingredient ingredient) {
        if (!validationService.validateIngredient(ingredient)) {
            throw new RuntimeException(ingredient.toString());
        }
        return ingredients.replace(id, ingredient);
    }

    @Override
    public Ingredient delete(Long id) {
        if (ingredients.containsKey(id)) {
            throw new RuntimeException();
        }
        return ingredients.remove(id);
    }

    @Override
    public Map<Long, Ingredient> getAll() {
        return ingredients;
    }
}
