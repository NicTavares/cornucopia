package com.example.controller;

import com.example.DAO.RecipeDAO;
import com.example.DAO.UtilDAO;
import com.example.models.*;
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
    public String searchRecipeResults(@ModelAttribute StatisticsParams StatisticsParams, Model model) {
        String field = StatisticsParams.getField();
        String operator = StatisticsParams.getOperator();
        List<Recipe> results = utilDAO.searchStatistics(field, operator);

        model.addAttribute("searchResult", results);
        return "searchRecipeResult";
    }


    @GetMapping("/getUsrStatistics")
    public String getUsrStatistics(Model model) {
        model.addAttribute("SearchParams", new SearchParams());
        model.addAttribute("SearchParams", new SearchParams());
        return "usrStatistics";
    }


    @PostMapping("/getUsrStatisticsResults")
    public String getUsrStatisticsResults(@ModelAttribute SearchParams searchParams, Model model) {
        String field = searchParams.getField();
        String operator = searchParams.getOperator();
        float value = searchParams.getValue();
        List<UsrStatistics> results = utilDAO.getUsrStatistics(field, operator,value);

        model.addAttribute("searchResult", results);
        return "searchUsrResult";
    }


}
