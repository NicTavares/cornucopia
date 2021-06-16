package com.example.controller;

import com.example.DAO.RecipeDAO;
import com.example.DAO.UtilDAO;
import com.example.models.Recipe;
import com.example.models.RecipePayload;
import com.example.models.SearchParams;
import com.example.models.StatisticsParams;
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
public class StatisticsController {

    @Autowired
    UtilDAO utilDAO;
    @GetMapping("/getStatistics")
    public String searchRecipes(Model model) {
        model.addAttribute("StatisticsParams", new StatisticsParams());
        model.addAttribute("StatisticsResults", new StatisticsParams());
        return "statistics";
    }


    @PostMapping("/getStatisticsResults")
    public String searchRecipeResults(@ModelAttribute SearchParams searchParams, Model model) {
        String field = searchParams.getField();
        String operator = searchParams.getOperator();
        List<Recipe> results = utilDAO.searchStatistics(field, operator);

        model.addAttribute("searchResult", results);
        return "searchRecipeResult";
    }



}
