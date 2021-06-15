package com.example.controller;

import com.example.DAO.*;
import com.example.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
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
    @Autowired
    CommentDAO commentDAO;
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



//    static class RecipePayload {
//        public Recipe recipe;
//        public Optional<List<String>> tags;
//        public  Optional<List<String>> ingredients;
//        public  Optional<List<String>> equipments;
//        public  Optional<List<String>> techniques;
//
//
//    }

//    @PostMapping(path = "/uploadRecipe")
//    public ResponseEntity uploadRecipe(@RequestBody RecipePayload recipePayload) {
//
//        int currentUUID=recipeDAO.getNextUUID();
//        recipePayload.recipe.setUUID(currentUUID);
//        recipeDAO.create(recipePayload.recipe);
//
//        if (!recipePayload.tags.isEmpty()) {
//            recipePayload.tags.get().forEach(name -> {
//                if(tagDAO.get(name).isEmpty() ){
//                    Tag tag=new Tag(name);
//                    tagDAO.create(tag);
//                }
//                recipeDAO.createRecipeTag(currentUUID,name);
//            });
//        }
//        if (!recipePayload.ingredients.isEmpty()) {
//            recipePayload.ingredients.get().forEach(name -> {
//                if(ingredientDAO.get(name).isEmpty() ){
//                    Ingredient ingredient=new Ingredient(name);
//                    ingredientDAO.create(ingredient);
//                }
//                recipeDAO.createRecipeIngredient(currentUUID,name);
//            });
//        }
//        if (!recipePayload.equipments.isEmpty()) {
//            recipePayload.equipments.get().forEach(name -> {
//                if(equipmentDAO.get(name).isEmpty() ){
//                    Equipment equipment=new Equipment(name);
//                    equipmentDAO.create(equipment);
//                }
//                recipeDAO.createRecipeEquipment(currentUUID,name);
//            });
//        }
//
//        if (!recipePayload.techniques.isEmpty()) {
//            recipePayload.techniques.get().forEach(name -> {
//                if(techniqueDAO.get(name).isEmpty() ){
//                    Technique technique=new Technique(name,5);
//                    techniqueDAO.create(technique);
//                }
//                recipeDAO.createRecipeTechnique(currentUUID,name);
//            });
//        }
//
//
//        return ResponseEntity.ok("New recipe added");
//    }



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


    @PostMapping(path = "/postComment")
    public ResponseEntity postComment(@RequestBody Comment comment) {
        int cnumber = commentDAO.getNextCommentNumber(Integer.toString(comment.getRecipeUUID()));
        comment.setCommentNumber(cnumber);
        commentDAO.create(comment);

        return ResponseEntity.ok(cnumber);
    }

    @GetMapping(path = "/getComment/{recipeUUID}")
    public List<Comment> getComment(@PathVariable int recipeUUID) {
        return commentDAO.getComment(Integer.toString(recipeUUID));
    }

    @GetMapping(path = "/getTags/{recipeUUID}")
    public List<RecipeTag> getTags(@PathVariable int recipeUUID) {
        return recipeDAO.getTagsByRecipe(Integer.toString(recipeUUID));
    }

    @GetMapping(path = "/getIngredients/{recipeUUID}")
    public List<RecipeIngredient> getIngredients(@PathVariable int recipeUUID) {
        return recipeDAO.getIngredientsByRecipe(Integer.toString(recipeUUID));
    }

    @GetMapping(path = "/getEquipment/{recipeUUID}")
    public List<RecipeEquipment> getEquipment(@PathVariable int recipeUUID) {
        return recipeDAO.getEquipmentByRecipe(Integer.toString(recipeUUID));
    }

    @GetMapping(path = "/getTechnique/{recipeUUID}")
    public List<RecipeTechnique> getTechnique(@PathVariable int recipeUUID) {
        return recipeDAO.getTechniqueByRecipe(Integer.toString(recipeUUID));
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
