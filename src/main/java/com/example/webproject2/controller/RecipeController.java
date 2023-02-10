package com.example.webproject2.controller;

import com.example.webproject2.model.Recipe;
import com.example.webproject2.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/")
    public ResponseEntity<Recipe> add(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Recipe> get(@PathVariable long id) {
        return ResponseEntity.of(recipeService.getId(id));
    }

}
