package com.example.controller;

import com.example.DAO.UtilDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilController {
    @Autowired
    UtilDAO utilDAO;
    @GetMapping(path = "/getUsrTakeAllCourses")
    public String getUsrTakeAllCourses(Model model) {

        model.addAttribute("searchResult", utilDAO.usrNameTakeAllCourse());
        return "usrTakeAllCourses";
    }

}
