package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user){
        UserDetails userByUsername = userService.loadUserByUsername(user.getUsername());
        if (userByUsername == null){
            userService.save(user);
        }
        return "redirect:/";
    }
}
