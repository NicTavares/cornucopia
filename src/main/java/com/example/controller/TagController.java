package com.example.controller;

import com.example.DAO.TagDAO;
import com.example.DAO.UsrDAO;
import com.example.models.Tag;
import com.example.models.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TagController {
    @Autowired
    TagDAO tagDAO;

    @GetMapping(path="/getAllTag")
    public List<Tag> getAllTag()
    {
        return tagDAO.list();
    }

    @GetMapping(path="/getTag/{name}")
    public Optional<Tag> getTag(@PathVariable String name)
    {
        return tagDAO.get(name);
    }

}
