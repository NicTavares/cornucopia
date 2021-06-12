

package com.example.controller;

import com.example.DAO.UsrDAO;
import com.example.models.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        usr.setUUID(usrDAO.geNextUUID());
        usrDAO.create(usr);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("new user added ");
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

    @PostMapping(path="/favouriteRecipe/{usrUUID}/{recipeUUID}")
    public ResponseEntity<String> favouriteRecipe( @PathVariable int usrUUID, @PathVariable int recipeUUID)
    {
        usrDAO.createFavouriteRecipe(usrUUID,recipeUUID);
        return ResponseEntity.ok("favourite added");
    }
    @GetMapping(path="/getFavourites/{UUID}")
    //todo
    public List<Integer> getFavourites(@PathVariable int UUID)
    {
        return usrDAO.getFavouriteRecipes(UUID);
    }
//
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