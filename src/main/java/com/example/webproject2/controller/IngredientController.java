package com.example.webproject2.controller;

import com.example.webproject2.model.Ingredient;
import com.example.webproject2.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "API по работе с ингредиентами", description = "CRUD-операции для ингредиентов")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/")
    @Operation(
            summary = "Добавление ингредиента"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент добавлен"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Ingredient> add(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.add(ingredient));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "получение  ингредиента по id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент получен"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Ingredient> get(@PathVariable long id) {
        return ResponseEntity.of(ingredientService.getId(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление ингредиента по id и рецепту"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент обновлен"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Ingredient> update(@PathVariable long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.update(id, ingredient));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент удален"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Ingredient> delete(@PathVariable long id) {
        return ResponseEntity.ok(ingredientService.delete(id));
    }

    @GetMapping("/")
    @Operation(
            summary = "Выдача всех ингредиентов"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все ингредиенты выданы"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере")}
    )
    public ResponseEntity<Map<Long, Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientService.getAll());
    }


}
