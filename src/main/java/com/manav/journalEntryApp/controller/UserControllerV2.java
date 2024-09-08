package com.manav.journalEntryApp.controller;
import com.manav.journalEntryApp.Respository.UserEntryRepository;
import com.manav.journalEntryApp.entity.User;
import com.manav.journalEntryApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserControllerV2 {

    @Autowired
    private UserEntryService userService;

    @Autowired
    private UserEntryRepository userEntryRepository;

    @PutMapping
    public ResponseEntity<?> updateuser(@RequestBody User user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User userINdb = userService.findByUsername(username);

        userINdb.setUsername(user.getUsername());
        userINdb.setPassword(user.getPassword());
        userService.saveEntry(userINdb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteuserById(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}