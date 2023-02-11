package com.example.webproject2.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Ingredient {
    private String nameIng;
    private int count;
    private String measure;
}
