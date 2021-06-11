package com.example.controller;

import com.example.DAO.IngredientDAO;
import com.example.DAO.TagDAO;
import com.example.models.Equipment;
import com.example.models.Ingredient;
import com.example.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {


    @Autowired
    IngredientDAO ingredientDAO;

    @GetMapping(path="/getAllIngredient")
    public List<Ingredient> getAllIngredient()
    {
        return ingredientDAO.list();
    }


    @PostMapping(path="/addIngredient")
    public ResponseEntity addIngredient(@RequestBody Ingredient ingredient)
    {
        ingredientDAO.create(ingredient);
        return ResponseEntity.ok("New ingredient added");
    }


}
