package com.example.controller;

import com.example.DAO.RecipeDAO;
import com.example.models.Recipe;
import com.example.models.RecipePayload;
import com.example.models.SearchParams;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

@Controller
public class SearchRecipeController {

    @Autowired
    RecipeDAO recipeDAO;

    @GetMapping("/searchRecipe")
    public String searchRecipes(Model model) {
        model.addAttribute("SearchParams", new SearchParams());
        model.addAttribute("searchResult", new SearchParams());
        return "searchRecipes";
    }


    @PostMapping("/searchRecipe")
    public String searchRecipeResults(@ModelAttribute SearchParams searchParams, Model model) {
        String field = searchParams.getField();
        String operator = searchParams.getOperator();
        float value = searchParams.getValue();

        List<Recipe> results = recipeDAO.searchWhere(field, operator, value);

        model.addAttribute("searchResult", results);
        return "searchRecipeResult";
    }

}
