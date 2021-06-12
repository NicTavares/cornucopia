package com.example.controller;

import com.example.DAO.*;
import com.example.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {

    @Autowired
    RecipeDAO recipeDAO;
    @Autowired
    TagDAO tagDAO;
    @Autowired
    IngredientDAO ingredientDAO;
    @Autowired
    EquipmentDAO equipmentDAO;
    @Autowired
    TechniqueDAO techniqueDAO;

    @Autowired
    RatingDAO ratingDAO;
    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);

    @GetMapping(path = "/getAllRecipe")
    public List<Recipe> getAllRecipe() {
        return recipeDAO.list();
    }

    @GetMapping(path = "/getRecipe/{UUID}")
    public Optional<Recipe> getRecipe(@PathVariable int UUID) {
        return recipeDAO.get(Integer.toString(UUID));
    }

    @PostMapping(path = "/createRecipe")
    public ResponseEntity createRecipe(@RequestBody Recipe recipe) {
        recipeDAO.create(recipe);
        return ResponseEntity.ok("New recipe added");
    }


    static class RecipePayload {
        public Recipe recipe;
        public Optional<List<String>> tags;
        public  Optional<List<String>> ingredients;
        public  Optional<List<String>> equipments;
        public  Optional<List<String>> techniques;


    }

//    {
//        "recipe":{
//        "UUID":11,
//                "name":"test recipe",
//                "text":"test context",
//                "averageScore":0,
//                "estimatedTime":15,
//                "uploaderUUID":11111
//    },
//        "tags":["testtag1","testtag2"],
//        "ingredients":["test_ingredient_1","test_ingredient_2"],
//        "equipments":["test_equipment_1","test_equipment_2"],
//        "techniques":["test_techniques_1","test_techniques_2"]
//    }

    @PostMapping(path = "/uploadRecipe")
    public ResponseEntity uploadRecipe(@RequestBody RecipePayload recipePayload) {

        int currentUUID=recipeDAO.getNextUUID();
        recipePayload.recipe.setUUID(currentUUID);
        recipeDAO.create(recipePayload.recipe);

        if (!recipePayload.tags.isEmpty()) {
            recipePayload.tags.get().forEach(name -> {
                if(tagDAO.get(name).isEmpty() ){
                    Tag tag=new Tag(name);
                    tagDAO.create(tag);
                }
                recipeDAO.createRecipeTag(currentUUID,name);
            });
        }
        if (!recipePayload.ingredients.isEmpty()) {
            recipePayload.ingredients.get().forEach(name -> {
                if(ingredientDAO.get(name).isEmpty() ){
                    Ingredient ingredient=new Ingredient(name);
                    ingredientDAO.create(ingredient);
                }
                recipeDAO.createRecipeIngredient(currentUUID,name);
            });
        }
        if (!recipePayload.equipments.isEmpty()) {
            recipePayload.equipments.get().forEach(name -> {
                if(equipmentDAO.get(name).isEmpty() ){
                    Equipment equipment=new Equipment(name);
                    equipmentDAO.create(equipment);
                }
                recipeDAO.createRecipeEquipment(currentUUID,name);
            });
        }

        if (!recipePayload.techniques.isEmpty()) {
            recipePayload.techniques.get().forEach(name -> {
                if(techniqueDAO.get(name).isEmpty() ){
                    Technique technique=new Technique(name,5);
                    techniqueDAO.create(technique);
                }
                recipeDAO.createRecipeTechnique(currentUUID,name);
            });
        }


        return ResponseEntity.ok("New recipe added");
    }

    static class UsrRateRecipePayload {
        public int usrUUID;
        public int recipeUUID;
        public int score;
    }

    @PostMapping(path = "/usrRateRecipe")
    public ResponseEntity usrRateRecipe(@RequestBody UsrRateRecipePayload usrRateRecipePayload) {
        log.info(String.format("%d",usrRateRecipePayload.usrUUID));
        log.info(String.format("%d",usrRateRecipePayload.recipeUUID));
        ratingDAO.create(usrRateRecipePayload.usrUUID,usrRateRecipePayload.recipeUUID,usrRateRecipePayload.score);
        recipeDAO.updateScore(Integer.toString(usrRateRecipePayload.recipeUUID),ratingDAO.getScore(usrRateRecipePayload.recipeUUID));
        return ResponseEntity.ok("rated successfully");
    }
//    static class commentPayload {
//        int commenterUUID;
//        int recipeUUID;
//        int commentNumber;
//        String text;
//
//    }

//    @PostMapping(path = "/postComment")
//    public ResponseEntity postComment(@RequestBody RecipePayload recipePayload) {
//
//    }


//{
//    "UUID":10,
//    "name":"test recipe",
//    "text":"test context",
//    "averageScore":0,
//    "estimatedTime":15,
//    "uploaderUUID":11111
//}

    }
