package com.example.controller;

import com.example.DAO.UsrDAO;
import com.example.models.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @Autowired
    UsrDAO usrDAO;

    @GetMapping("/signup")
    public String signup(Model model) {
        Usr usr = new Usr();
        model.addAttribute("Usr", usr);
        return "signup";
    }

    @PostMapping("/signup")
    public String greetingSubmit(@ModelAttribute Usr usr, Model model) {
        usr.setUUID(usrDAO.getNextUUID());
        usrDAO.create(usr);
        return "success";
    }
}
