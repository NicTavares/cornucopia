package com.example.controller;

import com.example.DAO.RecipeDAO;
import com.example.models.RecipePayload;
import com.example.models.SearchParams;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchRecipeController {
    @GetMapping("/searchRecipe")
    public String searchRecipes(@ModelAttribute SearchParams searchParams, Model model) throws JSONException {
        model.addAttribute("SearchParams", searchParams);

        JSONObject obj = new JSONObject();
        obj.put("field", searchParams.getField());
        obj.put("operator", searchParams.getOperator());
        obj.put("value", searchParams.getValue());



        model.addAttribute("searchResult", obj.toString());
        return "searchRecipes";
    }

}
