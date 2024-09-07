package com.manav.journalEntryApp.service;

import com.manav.journalEntryApp.Respository.JournalEntryRepository;
import com.manav.journalEntryApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.manav.journalEntryApp.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JournalEntryService{

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){

        User user = userEntryService.findByUsername(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntryList().add(saved);
        userEntryService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> getAllJournalEntries(){

        return journalEntryRepository.findAll();
    }
    public JournalEntry findById(ObjectId id){

        return journalEntryRepository.findById(id).orElse(null);
    }
    public void deleteByID(ObjectId id, String username){

        User user = userEntryService.findByUsername(username);
        user.getJournalEntryList().remove(findById(id));
        userEntryService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
