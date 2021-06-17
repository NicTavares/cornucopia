package com.example.controller;

import com.example.DAO.AdministratorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministratorController {
    static class AuthPayload {
        public int UUID;
        public String password;
    }
    @Autowired
    AdministratorDAO administratorDAO;
    @GetMapping(path="/authAdmin")
    public ResponseEntity authAdmin(@RequestBody AuthPayload authPayload)
    {

        if(administratorDAO.getPasswordByUUID(authPayload.UUID).isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Wrong username");
        }
        else{
            if (administratorDAO.getPasswordByUUID(authPayload.UUID).get().equals(authPayload.password)){
                return ResponseEntity.ok("ok");
            }
            else return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Wrong password");
        }
    }
}
