package com.example.webproject2.service.impl;

import com.example.webproject2.model.Recipe;
import com.example.webproject2.service.FileService;
import com.example.webproject2.service.RecipeService;
import com.example.webproject2.service.ValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    public RecipeServiceImpl(FileService fileService, ValidationService validationService) {
        this.fileService = fileService;
        this.validationService = validationService;
    }

    @Override
    public Recipe add(Recipe recipe) {
        if (!validationService.validateRecipe(recipe)) {
            throw new RuntimeException(recipe.toString());
        }
        saveFile();
        return recipes.put(idRecipe++, recipe);
    }

    @Override
    public Optional<Recipe> getId(Long id) {
        readFile();
        return Optional.ofNullable(recipes.get(id));
    }

    @Override
    public Recipe update(Long id, Recipe recipe) {
        if (!validationService.validateRecipe(recipe)) {
            throw new RuntimeException();
        }
        saveFile();
        return recipes.replace(id, recipe);
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
        readFile();
        return recipes;
    }

    private void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveFile(json, recipeFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    @PostConstruct
    private void readFile() {
        try {
            String json = fileService.readFile(recipeFileName);
            recipes = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

