package com.manav.journalEntryApp.service;

import com.manav.journalEntryApp.Respository.UserEntryRepository;
import com.manav.journalEntryApp.entity.JournalEntry;
import com.manav.journalEntryApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    public void saveEntry(User user){

        userEntryRepository.save(user);
    }
    public List<User> getAllUserEntries(){

        return userEntryRepository.findAll();
    }
    public User findById(ObjectId id){

        return  userEntryRepository.findById(id).orElse(null);
    }
    public void deleteByID(ObjectId id){
        userEntryRepository.deleteById(id);
    }
    public User findByUsername(String username){
        return userEntryRepository.findByUsername(username);
    }
}
