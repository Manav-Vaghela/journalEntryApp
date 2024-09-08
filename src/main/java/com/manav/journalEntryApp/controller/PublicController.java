package com.manav.journalEntryApp.controller;

import com.manav.journalEntryApp.entity.User;
import com.manav.journalEntryApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserEntryService userEntryService;

    @PostMapping("/creat-user")
    public void createuser(@RequestBody User user){

        userEntryService.saveEntry(user);
    }
}
