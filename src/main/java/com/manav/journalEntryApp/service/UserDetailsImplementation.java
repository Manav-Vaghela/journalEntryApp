package com.manav.journalEntryApp.service;

import com.manav.journalEntryApp.Respository.UserEntryRepository;
import com.manav.journalEntryApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsImplementation implements UserDetailsService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userEntryRepository.findByUsername(username);

        if(user != null) {

              return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();

        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}
