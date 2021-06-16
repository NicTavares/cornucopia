package com.example.controller;


import com.example.DAO.CourseDAO;
import com.example.models.CourseInfoPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GetCourseInfoController {

    @Autowired
    CourseDAO courseDAO;


    @GetMapping("/getcourseinfo")
    public String getCourseInfo(Model model) {
        model.addAttribute("courseInfoPayload", new CourseInfoPayload());
        return "getcourseinfo";
    }

    @PostMapping("/getcourseinfo")
    public String getInfo(Model model, @ModelAttribute CourseInfoPayload courseInfoPayload) {
        List<String> columns = new ArrayList<>();
        if(courseInfoPayload.isUUID()) { columns.add("UUID"); }
        if(courseInfoPayload.isText()) { columns.add("text"); }
        if(courseInfoPayload.isLength()) { columns.add("length"); }
        if(courseInfoPayload.isName()) { columns.add("name"); }
        if(courseInfoPayload.isRequirementName()) { columns.add("requirementName"); }
        if(courseInfoPayload.isCreatorUUID()) { columns.add("creatorUUID"); }
        List<Map<String, Object>> rows = courseDAO.projection(columns);
        System.out.println(rows);
        model.addAttribute("rows", rows);
        return "courseInfoResults";
    }
}
