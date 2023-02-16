package com.example.webproject2.service.impl;

import com.example.webproject2.model.Ingredient;
import com.example.webproject2.model.Recipe;
import com.example.webproject2.service.FileService;
import com.example.webproject2.service.RecipeService;
import com.example.webproject2.service.ValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Path;
import java.util.LinkedHashMap;

import java.util.Map;
import java.util.Optional;


@Service
public class RecipeServiceImpl implements RecipeService {
    private final FileService fileService;
    private static long idRecipe = 1;
    private Map<Long, Recipe> recipes = new LinkedHashMap<>();
    private final ValidationService validationService;
    @Value("${name.of.recipe.file}")
    private String recipeFileName;
    @Value("${name.of.recipe.file.txt}")
    private String recipeFileNameTxt;
    @Value("${path.to.file}")
    private String recipePath;

    public RecipeServiceImpl(FileService fileService, ValidationService validationService) {
        this.fileService = fileService;
        this.validationService = validationService;
    }

    @Override
    public Recipe add(Recipe recipe) {
        if (!validationService.validateRecipe(recipe)) {
            throw new RuntimeException(recipe.toString());
        }
        recipes.put(idRecipe++, recipe);
        saveFile();
        return recipe;
    }

    @Override
    public Optional<Recipe> getId(Long id) {
        return Optional.ofNullable(recipes.get(id));
    }

    @Override
    public Recipe update(Long id, Recipe recipe) {
        if (!validationService.validateRecipe(recipe)) {
            throw new RuntimeException();
        }
        recipes.replace(id, recipe);
        saveFile();
        return recipe;
    }

    @Override
    public Recipe delete(Long id) {
        if (recipes.containsKey(id)) {
            throw new RuntimeException();
        }
        return recipes.remove(id);

    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipes;
    }

    private void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveFile(json, recipeFileName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public File addRecipe() throws IOException {
        return fileService.saveRecipeTxt(recipeToString(), Path.of(recipePath, recipeFileNameTxt)).toFile();
    }


    @PostConstruct
    private void readFile() {
        try {
            String json = fileService.readFile(recipeFileName);
            recipes = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private String recipeToString() {
        StringBuilder sb = new StringBuilder();
        for (Recipe recipe : recipes.values()) {
            sb.append("\n").append(recipe.toString()).append("\n");
            sb.append("\nИнгредиенты\n");
            int i = 1;
            for (Ingredient ingredient : recipe.getIngredients()) {
                sb.append(i++).append(ingredient.toString()).append("\n");
            }
            i = 1;
            sb.append("\nШаг приготовления\n");
            for (String step : recipe.getSteps()) {
                sb.append(i++).append(step).append("\n");
            }
        }
        return sb.append("\n").toString();
    }
}

