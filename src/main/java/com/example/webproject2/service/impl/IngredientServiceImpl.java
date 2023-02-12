package com.example.webproject2.service.impl;

import com.example.webproject2.model.Ingredient;
import com.example.webproject2.service.FileService;
import com.example.webproject2.service.IngredientService;
import com.example.webproject2.service.ValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class IngredientServiceImpl implements IngredientService {
    private final FileService fileService;

    private static long idIngredient = 1;
    private Map<Long, Ingredient> ingredients = new LinkedHashMap<>();
    private final ValidationService validationService;
    @Value("${name.of.ingredient.file}")
    private String ingredientName;

    public IngredientServiceImpl(FileService fileService, ValidationService validationService) {
        this.fileService = fileService;
        this.validationService = validationService;


    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        if (!validationService.validateIngredient(ingredient)) {
            throw new RuntimeException(ingredient.toString());
        }
        saveFile();
        return ingredients.put(idIngredient++, ingredient);
    }

    @Override
    public Optional<Ingredient> getId(Long id) {
        readFile();
        return Optional.ofNullable(ingredients.get(id));
    }

    @Override
    public Ingredient update(Long id, Ingredient ingredient) {
        if (!validationService.validateIngredient(ingredient)) {
            throw new RuntimeException(ingredient.toString());
        }
        saveFile();
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
        readFile();
        return ingredients;
    }

    private void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.saveFile(json, ingredientName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private void readFile() {
        try {
            String json = fileService.readFile(ingredientName);
            ingredients = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
