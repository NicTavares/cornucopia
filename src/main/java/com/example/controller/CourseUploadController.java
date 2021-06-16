package com.example.controller;

import com.example.DAO.AdministratorDAO;
import com.example.DAO.CourseDAO;
import com.example.DAO.TechniqueDAO;
import com.example.models.Administrator;
import com.example.models.Course;
import com.example.models.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CourseUploadController {

    @Autowired
    CourseDAO courseDAO;
    @Autowired
    AdministratorDAO administratorDAO;
    @Autowired
    TechniqueDAO techniqueDAO;

    @GetMapping("/uploadcourse")
    public String uploadCourse(Model model) {
        Course course = new Course();
        model.addAttribute("Course", course);
        return "upload";
    }

    @PostMapping("/uploadcourse")
    public String uploaded(Model model, @ModelAttribute Course course) {
        System.out.println(course);
        Optional<Technique> t = techniqueDAO.get(course.getRequirementName());
        Optional<Administrator> a = administratorDAO.get(Integer.toString(course.getCreatorUUID()));
        if(t.isEmpty()) {
            Technique newTechnique = new Technique(course.getRequirementName(), 0);
            techniqueDAO.create(newTechnique);
        }
        if(a.isEmpty()) {
            return "invalidadmin";
        }
        course.setUUID(courseDAO.getNextUUID());
        courseDAO.create(course);
        return "success";
    }
}
