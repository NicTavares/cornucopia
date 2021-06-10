package com.example.controller;

import com.example.DAO.RecipeDAO;
import com.example.models.Recipe;
import com.example.models.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {
    @Autowired
    RecipeDAO recipeDAO;

    @GetMapping(path="/getAllRecipe")
    public List<Recipe> getAllRecipe()
    {
        return recipeDAO.list();
    }

    @GetMapping(path="/getRecipe/{UUID}")
    public Optional<Recipe> getRecipe(@PathVariable int UUID)
    {
        return  recipeDAO.get(Integer.toString(UUID));
    }
    @PostMapping(path="/createRecipe")
    public ResponseEntity createRecipe(@RequestBody Recipe recipe)
    {
        recipeDAO.create(recipe);
        return ResponseEntity.ok("New recipe added");
    }


//{
//    "UUID":10,
//    "name":"test recipe",
//    "text":"test context",
//    "averageScore":0,
//    "estimatedTime":15,
//    "uploaderUUID":11111
//}

}
