package com.example.controller;

import com.example.DAO.UtilDAO;
import com.example.models.DashBoardParams;
import com.example.models.SearchParams;
import com.example.models.Usr;
import com.example.models.UsrStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UtilController {
    @Autowired
    UtilDAO utilDAO;
    @GetMapping(path = "/getUsrDashBoard")
    public String getUsrDashBoard(Model model) {
        model.addAttribute("DashBoardParams", new DashBoardParams());
        model.addAttribute("DashBoardResults",new DashBoardParams());
        return "usrDashboard";
    }

    @PostMapping("/getUsrDashboardResults")
    public String getUsrDashboardResults(@ModelAttribute DashBoardParams dashBoardParams, Model model) {

        String field = dashBoardParams.getField();

        List<Usr> results = utilDAO.getUsrDashboard(field);

        model.addAttribute("searchResult", results);
        return "usrDashBoardResults";
    }



}
