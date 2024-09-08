package com.manav.journalEntryApp.controller;
import com.manav.journalEntryApp.entity.User;
import com.manav.journalEntryApp.entity.JournalEntry;
import com.manav.journalEntryApp.service.JournalEntryService;
import com.manav.journalEntryApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalentryservice;

    @Autowired
    private UserEntryService userentryservice;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAlljournalentryforusers(@PathVariable String username) {

        User user = userentryservice.findByUsername(username);

        List<JournalEntry> all = user.getJournalEntryList();
        if(all != null && !all.isEmpty()){

            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myentry, @PathVariable String username) {

        try{
            myentry.setDate(LocalDateTime.now());
            journalentryservice.saveEntry(myentry,username);
            return new ResponseEntity<>(myentry, HttpStatus.CREATED);
        }
        catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){

        Optional<JournalEntry> journalEntry = Optional.ofNullable(journalentryservice.findById(myId));

        if(journalEntry.isPresent()){

            return  new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/   id/{username}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String username){

        journalentryservice.deleteByID(myId,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{username}/{myId}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId myId,
                                         @RequestBody JournalEntry newentry,
                                         @PathVariable String username){

        JournalEntry old = journalentryservice.findById(myId);
        if(old != null){

            old.setTitle(newentry.getTitle() != null && !newentry.getTitle().equals("") ? newentry.getTitle() : old.getTitle());
            old.setContent(newentry.getContent() != null && !newentry.getContent().equals("") ? newentry.getContent() : old.getContent());

            journalentryservice.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
