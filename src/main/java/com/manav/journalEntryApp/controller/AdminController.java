package com.manav.journalEntryApp.controller;

import com.manav.journalEntryApp.entity.User;
import com.manav.journalEntryApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserEntryService userEntryService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getallusers() {

        List<User>  all = userEntryService.getAllUserEntries();

        if(all != null  && !all.isEmpty()) {

            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void adduser(@RequestBody User user) {

        userEntryService.saveadmin(user);
    }
}
