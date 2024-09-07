package com.manav.journalEntryApp.controller;
import com.manav.journalEntryApp.entity.User;
import com.manav.journalEntryApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserControllerV2 {

    @Autowired
    private UserEntryService userService;

    @GetMapping
    public List<User> getallusers(){

        return userService.getAllUserEntries();
    }

    @PostMapping
    public void createuser(@RequestBody User user){

        userService.saveEntry(user);
    }

    @PutMapping("/username")
    public ResponseEntity<?> updateuser(@RequestBody User user,@PathVariable String username){

        User userINdb = userService.findByUsername(username);

        if(userINdb != null){

            userINdb.setUsername(user.getUsername());
            userINdb.setPassword(user.getPassword());
            userService.saveEntry(userINdb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}