package com.example.webproject2.service;

import com.example.webproject2.model.Ingredient;
import com.example.webproject2.model.Recipe;

public interface ValidationService {
     boolean validateRecipe(Recipe recipe);
    boolean validateIngredient (Ingredient ingredient);


}
