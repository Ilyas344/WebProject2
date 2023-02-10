package com.example.webproject2.service.impl;

import com.example.webproject2.model.Recipe;
import com.example.webproject2.service.RecipeService;
import com.example.webproject2.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class RecipeServiceImpl implements RecipeService {
    private static long idRecipe=1;
    private Map<Long, Recipe> recipes =new LinkedHashMap<>();
   private final ValidationService validationService;

    public RecipeServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public Recipe add(Recipe recipe) {
        if(!validationService.validateRecipe(recipe))
            throw new RuntimeException(recipe.toString());
        return recipes.put(idRecipe++,recipe);
    }

    @Override
    public Optional<Recipe> getId(Long id) {
        return Optional.ofNullable(recipes.get(id));
    }
}
