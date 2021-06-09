

package com.example.controller;

import com.example.DAO.UsrDAO;
import com.example.models.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsrController
{
    @Autowired
    UsrDAO usrDAO;

    @GetMapping(path="/getAllUsr")
    public List<Usr> getAllUsr()
    {
        return usrDAO.list();
    }
    @GetMapping(path="/getUsr/{UUID}")
    public Optional<Usr> getUsr(@PathVariable int UUID)
    {
        return  usrDAO.get(Integer.toString(UUID));
    }

    @PostMapping(path="/addUsr")
    public ResponseEntity addUsr(@RequestBody Usr usr)
    {
        usrDAO.create(usr);
        return ResponseEntity.ok("New usr added");
    }

    @PutMapping(path="/updateUsr/{UUID}")
    public ResponseEntity<String> updateUsr(@RequestBody Usr usr, @PathVariable int UUID)
    {
        usrDAO.update(usr,Integer.toString(UUID));
        return ResponseEntity.ok(String.format("Usr %d : %s updated",UUID,usr.getName()));
    }

    @DeleteMapping(path="/deleteUsr/{UUID}")
    public ResponseEntity<String> deleteUsr(@PathVariable int UUID)
    {
        usrDAO.delete(Integer.toString(UUID));
        return ResponseEntity.ok(String.format("Usr %d is deleted",UUID));
    }
//Test request body:
//    {
//        "UUID":10,
//            "birthday":"1994-02-01",
//            "email":"test@gmail.com",
//            "username":"testusr",
//            "name":"Test One",
//            "password":"123456",
//            "city":"van",
//            "postalCode":"1234"
//    }
}