package com.manav.journalEntryApp.controller;
import com.manav.journalEntryApp.Api_Response.WeatherResponse;
import com.manav.journalEntryApp.Respository.UserEntryRepository;
import com.manav.journalEntryApp.entity.User;
import com.manav.journalEntryApp.service.UserEntryService;
import com.manav.journalEntryApp.service.WeatherService;
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

    @Autowired
    private WeatherService weatherService;

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

    @GetMapping
    public ResponseEntity<?> greetings(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");

        String greeting = "";

        if(weatherResponse != null){

            greeting =  ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("Hi" + authentication.getName() + greeting,HttpStatus.OK);
    }
}