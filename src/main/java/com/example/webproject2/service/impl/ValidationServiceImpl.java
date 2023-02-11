package com.example.webproject2.service.impl;

import com.example.webproject2.model.Ingredient;
import com.example.webproject2.model.Recipe;
import com.example.webproject2.service.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validateRecipe(Recipe recipe) {
        return recipe != null
                && !StringUtils.isBlank(recipe.getName())
                && recipe.getSteps() != null
                && !recipe.getIngredients().isEmpty()
                && !recipe.getSteps().isEmpty();
    }

    @Override
    public boolean validateIngredient(Ingredient ingredient) {
        return ingredient != null
                && !StringUtils.isBlank(ingredient.getNameIng())
                && !StringUtils.isBlank(ingredient.getMeasure());

    }

}
