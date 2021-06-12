

package com.example.controller;

import com.example.DAO.UsrDAO;
import com.example.models.Recipe;
import com.example.models.Usr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(UsrController.class);

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
    static class AuthPayload {
        public String username;
        public String password;
    }
    
    @GetMapping(path="/authUsr")
    public ResponseEntity authUsr(@RequestBody AuthPayload authPayload)
    {

        if(usrDAO.getPasswordByUsername(authPayload.username).isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Wrong username");
        }
        else{
            //log.info(usrDAO.getPasswordByUsername(authPayload.username).get());
            if (usrDAO.getPasswordByUsername(authPayload.username).get().equals(authPayload.password)){
                return ResponseEntity.ok("ok");
            }
            else return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Wrong password");
        }
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