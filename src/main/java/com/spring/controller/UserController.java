package com.spring.controller;

import com.spring.entities.Role;
import com.spring.entities.User;
import com.spring.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/signup")
    @Caching(evict = {@CacheEvict(value = "Users", allEntries = true)})
    public String signup(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        List<String> roles = new ArrayList<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        repo.save(user);
        return "User Created Successfully!!";
    }

    @PutMapping("/promote/{username}")
    @Caching(evict = {@CacheEvict(value = "Users", allEntries = true)})
    public String promote(@PathVariable("username") String username){
        User user = repo.findByUsername(username);
        List<String> roles = user.getRoles();
        roles.add(Role.ADMIN.name());
        user.setRoles(roles);
        repo.save(user);
        return "User promoted to Admin!!";
    }
}
