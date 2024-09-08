package com.manav.journalEntryApp.Respository;

import com.manav.journalEntryApp.entity.JournalEntry;
import com.manav.journalEntryApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);

    void deleteByUsername(String username);
}