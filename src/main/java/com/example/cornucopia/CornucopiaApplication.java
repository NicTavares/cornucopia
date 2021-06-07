package com.example.cornucopia;

import com.example.models.Equipment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

//        jdbcTemplate.execute("DROP TABLE IF EXISTS equipment");
//        jdbcTemplate.execute("CREATE TABLE equipment(" +
//                "name VARCHAR(255) PRIMARY KEY)");
//        // Split up the array of whole names into an array of first/last names
//        List<Object[]> names = new ArrayList<Object[]>();
//        Object[] value1 = new Object[] {
//                "knife1",};
//        Object[] value2 = new Object[] {
//                "knife2",};
//        names.add(value1);
//        names.add(value2);
//
//        // Use a Java 8 stream to print out each tuple of the list
//        names.forEach(name -> log.info(String.format("Inserting customer record for %s ", name)));
//
//        // Uses JdbcTemplate's batchUpdate operation to bulk load data
//        jdbcTemplate.batchUpdate("INSERT INTO equipment(name) VALUES (?)",  names);

        jdbcTemplate.query(
                "SELECT name FROM equipment WHERE name = ?", new Object[] { "knife1" },
                (rs, rowNum) -> new Equipment(rs.getString("name"))
        ).forEach(equ -> log.info("test1:equ"+equ.getName()+" exists"));
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/all_equ")
    public String showAllEquipments() {
        List<String> names = new ArrayList<>();
        jdbcTemplate.query(
                "SELECT * FROM equipment ", (rs, rowNum) -> new Equipment(rs.getString("name"))
        ).forEach(equ -> names.add(equ.getName()));
        names.forEach(name->log.info("test2"+name));

        String ret = "We have:"+String.join(", ", names)+ ":)!";

        return String.format("%s!", ret);
    }
    @GetMapping("/add_equ")//todo
    public void addEquipments() {
        jdbcTemplate.update("INSERT INTO equipment(name) VALUES ('new1')" );

    }
}
