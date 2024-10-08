package com.manav.journalEntryApp.service;

import com.manav.journalEntryApp.Respository.UserEntryRepository;
import com.manav.journalEntryApp.entity.JournalEntry;
import com.manav.journalEntryApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User user){ //save new users

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(user);
    }

    public void saveadmin(User user){ //save new users

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepository.save(user);
    }

    public void saveuser(User user){

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
