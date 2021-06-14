package com.example.controller;

import com.example.DAO.RecipeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteRecipeController {

    @Autowired
    RecipeDAO recipeDAO;

    @GetMapping("/delete")
    public String signup(Model model) {
        model.addAttribute("sourceText", "asdas");
        return "deleteRecipe";
    }

    @PostMapping("/delete")
    public String deleteRecipe(Model model, @RequestParam String recipeID) {
        int rows = recipeDAO.delete(recipeID);
        if(rows == 0) {
            return "notFound";
        } else {
            return "recipeDeleted";
        }
    }
}
