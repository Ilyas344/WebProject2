package com.example.webproject2.service;



import com.example.webproject2.model.Recipe;

import java.util.Optional;

public interface RecipeService {


        Recipe add(Recipe recipe);

        Optional<Recipe> getId(Long id);
}
