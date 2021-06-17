package com.example.cornucopia;

import com.example.models.Equipment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@ComponentScan("com.example")
@SpringBootApplication
@RestController
public class CornucopiaApplication implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(CornucopiaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CornucopiaApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        log.info("Creating tables");
    }


}
