package com.example.webproject2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String nameIng;
    private int count;
    private String measure;

    @Override
    public String toString() {
        return nameIng +
                " - " + count +
                " " + measure;
    }
}
