package com.example.webproject2.controller;

import com.example.webproject2.service.FileService;
import com.example.webproject2.service.RecipeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesControllers {
    @Value("${name.of.recipe.file}")
    private String recipeFileName;
    @Value("${name.of.ingredient.file}")
    private String ingredientName;
    private final FileService fileService;

    private final RecipeService recipeService;

    public FilesControllers(FileService fileService,RecipeService recipeService) {
        this.fileService = fileService;
        this.recipeService = recipeService;
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadFiles() throws FileNotFoundException {
        File file = fileService.getFromFile(recipeFileName);
        if (file.exists()) {

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipe.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/export/txt")
    public ResponseEntity<InputStreamResource> downloadFilesTxt() throws IOException {
        File file = recipeService.addRecipe();
        if (file.exists()) {

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipe.txt\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/importIngredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> uploadFilesIngredient(@RequestParam MultipartFile files) {
        fileService.clearFile(ingredientName);
        File file = fileService.getFromFile(ingredientName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            IOUtils.copy(files.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @PostMapping(value = "/importRecipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> uploadFilesRecipe(@RequestParam MultipartFile files) {
        fileService.clearFile(recipeFileName);
        File file = fileService.getFromFile(recipeFileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            IOUtils.copy(files.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
