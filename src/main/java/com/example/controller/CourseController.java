package com.example.controller;

import com.example.DAO.CourseDAO;
import com.example.DAO.TechniqueDAO;
import com.example.models.Course;
import com.example.models.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    CourseDAO courseDAO;
    @Autowired
    TechniqueDAO techniqueDAO;

    @GetMapping("/getAllCourse")
    public List<Course> getAllCourse() {
        return courseDAO.list();
    }


    @PostMapping("/uploadCourse")
    public ResponseEntity uploadCourse(@RequestBody Course course) {

        int currentUUID = courseDAO.getNextUUID();
        course.setUUID(currentUUID);
        System.out.println(course.getRequirementName());
        if (course.getRequirementName() != null) {
            Optional t = techniqueDAO.get(course.getRequirementName());
            if (t.isEmpty()) {
                Technique tech = new Technique(course.getRequirementName(), 5);
                techniqueDAO.create(tech);
            }
        }
        courseDAO.create(course);
        return ResponseEntity.ok("new course created");
    }

    @PostMapping("/usrTakeCourse/{courseUUID}/{usrUUID}")
    public ResponseEntity usrTakeCourse(@PathVariable int courseUUID, @PathVariable int usrUUID) {
        courseDAO.usrTakeCourse(usrUUID, courseUUID);
        return ResponseEntity.ok("new taking course created");
    }


//    "UUID":10015,
//    "text":"Water at 80 degrees...",
//    "length":5,
//    "name":"How to brew green tea",
//    "requirementName":"Green Tea",
//    "creatorUUID":11112

}
