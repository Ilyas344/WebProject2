package com.example.webproject2.controller;

import com.example.webproject2.model.Ingredient;
import com.example.webproject2.model.Recipe;
import com.example.webproject2.service.IngredientService;
import com.example.webproject2.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @PostMapping("/")
    public ResponseEntity<Ingredient> add (@RequestBody Ingredient ingredient){
        return ResponseEntity.ok(ingredientService.add(ingredient));
    }
    @PostMapping("/{id}")
    public ResponseEntity<Ingredient> get (@PathVariable long id){
        return ResponseEntity.of(ingredientService.getId(id));
    }

}
