package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class RegistrationController {
    @Value("${upload.path}")
    String path;
    @Autowired
    UserService userService;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, @RequestParam("file") MultipartFile file) throws IOException {
        UserDetails userByUsername = userService.loadUserByUsername(user.getUsername());
        uploadImage(user, file);
        if (userByUsername == null){
            userService.save(user);
        }
        return "redirect:/";
    }

    private void uploadImage(User user, MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + file.getOriginalFilename();
        user.setImage(fileName);
        Files.copy(file.getInputStream(), Path.of(path).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
    }
}
