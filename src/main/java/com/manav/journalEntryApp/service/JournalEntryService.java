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
        userEntryService.saveuser(user);
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

    @Transactional
    public boolean deleteByID(ObjectId id, String username){

        boolean removed = false;

        try {
            User user = userEntryService.findByUsername(username);

            removed = user.getJournalEntryList().removeIf(x -> x.getId().equals(id));

            if (removed){

                userEntryService.saveuser(user);
                journalEntryRepository.deleteById(id);
            }
            return removed;
        }catch (Exception e){

            System.out.println(e);
            throw new RuntimeException("An error occured while deleting journal entry",e);
        }
    }
}
