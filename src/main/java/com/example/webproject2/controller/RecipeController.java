package com.example.webproject2.controller;

import com.example.webproject2.model.Ingredient;
import com.example.webproject2.model.Recipe;
import com.example.webproject2.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "API по работе с рецептами", description = "CRUD-операции для рецептов")
public class RecipeController {
    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/")
    @Operation(
            summary = "Добавление рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт добавлен"),
            @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Recipe> add(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт получен"),
            @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Recipe> get(@PathVariable long id) {
        return ResponseEntity.of(recipeService.getId(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт обновлен"),
            @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Recipe> update(@PathVariable long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.update(id, recipe));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт удален"),
            @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Recipe> delete(@PathVariable long id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }

    @GetMapping("/")
    @Operation(
            summary = "Выдача всех рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все рецепты выданы"),
            @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Map<Long, Recipe>> getAll() {
        return ResponseEntity.ok(recipeService.getAll());
    }
}
