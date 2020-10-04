package com.spring.services;

import com.spring.components.CustomUserDetails;
import com.spring.entities.User;
import com.spring.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("Requsteed user " + s);
        System.out.println("*************** FIRST QUERY ********************");
        User user = userRepo.findByUsername(s);
        System.out.println("*************** SECOND QUERY ********************");
        User user1 = userRepo.findByUsername(s);
        System.out.println("Found user " + user);
        if(user == null){
            System.out.println("user not found !!");
            throw new UsernameNotFoundException("User not Found");
        }
        return new CustomUserDetails(user);
    }
}
