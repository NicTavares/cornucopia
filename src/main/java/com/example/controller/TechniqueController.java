package com.example.controller;

import com.example.DAO.TechniqueDAO;
import com.example.models.Technique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class TechniqueController {

    private static final Logger log = LoggerFactory.getLogger(TechniqueController.class);

    @Autowired
    TechniqueDAO techniqueDAO;

    @GetMapping(path="/getAllTechnique")
    public List<Technique> getAllTechnique()
    {
        return techniqueDAO.list();
    }

    @PostMapping(path="/addTechnique")
    public ResponseEntity addTechnique(@RequestBody Technique technique) {
        techniqueDAO.create(technique);
        return ResponseEntity.ok("New Technique added");
    }
}
