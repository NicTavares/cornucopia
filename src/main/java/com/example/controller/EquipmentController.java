package com.example.controller;

import com.example.DAO.EquipmentDAO;
import com.example.DAO.TagDAO;
import com.example.models.Equipment;
import com.example.models.Tag;
import com.example.models.Usr;
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
public class EquipmentController {

    private static final Logger log = LoggerFactory.getLogger(EquipmentController.class);

    @Autowired
    EquipmentDAO equipmentDAO;

    @GetMapping(path="/getAllEquipment")
    public List<Equipment> getAllEquipment()
    {
        return equipmentDAO.list();
    }

    @PostMapping(path="/addEquipment")
    public ResponseEntity addEquipment(@RequestBody Equipment equipment)
    {

         equipmentDAO.create(equipment);

        return ResponseEntity.ok("New Equipment added");
    }
}
