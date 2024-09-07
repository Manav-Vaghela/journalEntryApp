package com.manav.journalEntryApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health_check {

    @GetMapping
    public String healthCheck() {

        return "OK";
    }
}
