package com.example.controller;

import com.example.DAO.IngredientDAO;
import com.example.DAO.PantryDAO;
import com.example.models.Ingredient;
import com.example.models.Pantry;
import com.example.models.Technique;
import com.example.models.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PantryController {

    @Autowired
    PantryDAO pantryDAO;
    @Autowired
    IngredientDAO ingredientDAO;

    @GetMapping(path = "/getPantry/{usrUUID}")
    public List<Pantry> getPantry(@PathVariable int usrUUID) {

        return pantryDAO.getUsrPantry(Integer.toString(usrUUID));
    }

    @PostMapping(path="/addPantry")
    public ResponseEntity addUsr(@RequestBody Pantry pantry)
    {

        if(ingredientDAO.get(pantry.getName()).isEmpty() ){
            Ingredient ingredient=new Ingredient(pantry.getName());
            ingredientDAO.create(ingredient);
        }
        pantryDAO.create(pantry);

        return ResponseEntity.ok("New pantry added");
    }
}


