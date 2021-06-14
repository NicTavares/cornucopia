package com.example.controller;


import com.example.DAO.*;
import com.example.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UploadRecipeController {
    @Autowired
    RecipeDAO recipeDAO;
    @Autowired
    IngredientDAO ingredientDAO;
    @Autowired
    TagDAO tagDAO;
    @Autowired
    EquipmentDAO equipmentDAO;
    @Autowired
    TechniqueDAO techniqueDAO;

    @GetMapping("/uploadRecipe")
    public String uploadRecipe(Model model) {
        RecipePayload recipe = new RecipePayload();
        model.addAttribute("RecipePayload", recipe);
        return "recipeUpload";
    }

    @PostMapping("/uploadRecipe")
    public String greetingSubmit(@ModelAttribute RecipePayload recipe, Model model) {
        Recipe newRecipe = recipe.getRecipe();
        int currentUUID=recipeDAO.getNextUUID();
        newRecipe.setUUID(currentUUID);
        recipeDAO.create(newRecipe);

        String[] tags = recipe.getTags().split(",");
        for(int i = 0; i < tags.length; i++){
            Optional<Tag> foundTag = tagDAO.get(tags[i]);
            if(foundTag.isEmpty()) {
                Tag temp = new Tag(tags[i]);
                tagDAO.create(temp);
            }
            recipeDAO.createRecipeTag(currentUUID,tags[i]);
        }
        String[] ingredients = recipe.getIngredients().split(",");
        for(int i = 0; i < ingredients.length; i++) {
            Optional<Ingredient> foundIngredient = ingredientDAO.get(ingredients[i]);
            if(foundIngredient.isEmpty()) {
                Ingredient ingredient = new Ingredient(ingredients[i]);
                ingredientDAO.create(ingredient);
            }
            recipeDAO.createRecipeIngredient(currentUUID,ingredients[i]);
        }
        String[] equipments = recipe.getEquipment().split(",");
        for(int i = 0; i < equipments.length; i++) {
            Optional<Equipment> eq = equipmentDAO.get(equipments[i]);
            if(eq.isEmpty()) {
                Equipment e = new Equipment(equipments[i]);
                equipmentDAO.create(e);
            }
            recipeDAO.createRecipeEquipment(currentUUID,equipments[i]);
        }
        String[] techniques = recipe.getTechniques().split(",");
        for(int i = 0; i < techniques.length; i++){
            Optional<Technique> t = techniqueDAO.get(techniques[i]);
            if(t.isEmpty()) {
                Technique tech = new Technique(techniques[i], recipe.getDifficulty());
                techniqueDAO.create(tech);
            }
            recipeDAO.createRecipeTechnique(currentUUID,techniques[i]);
        }
        return "success";
    }
}
