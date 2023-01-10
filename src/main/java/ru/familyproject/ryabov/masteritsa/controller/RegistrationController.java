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
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * EN: Controller for working with the site registration form<br>
 * RU: Контроллер для работы с формой регистрации сайта
 *
 * @author Danila Ryabov
 * @version 1.0
 */
@Controller
public class RegistrationController {
    /**
     * EN: Path field to save the image from the registration form<br>
     * RU: Поле пути к сохранению картинки из формы регистрации
     */
    @Value("${upload.path}")
    String path;

    /**
     * EN: Service for working with entities <b>User</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>User</b> в БД
     */
    @Autowired
    private UserService userService;

    /**
     * EN: GET method to the page with the user registration form<br>
     * RU: GET метод к странице с формой регистрации пользователя
     * @return file registration.html
     */
    @GetMapping(Endpoints.REGISTRATION)
    public String registration(){
        return "registration";
    }

    /**
     * EN: Service for working with entities <b>User</b> in the database<br>
     * RU: POST метод со страницы "registration" с редиректом на страницу "/" при успешной регистрации
     * @return file main.html
     */
    @PostMapping(Endpoints.REGISTRATION)
    public String addUser(User user, @RequestParam("file") MultipartFile file){
        UserDetails userByUsername = userService.loadUserByUsername(user.getUsername());
        if (userByUsername == null){
            if (!file.isEmpty()){
                uploadImage(user, file);
            }else {
                user.setImage("avatar.png");
            }
            userService.save(user);
        }
        return "redirect:/";
    }

    /**
     * EN: Utility method for saving a picture in a folder on a machine<br>
     * RU: Служебный метод для сохранения картинки в папке на машине
     */
    private void uploadImage(User user, MultipartFile file){
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + file.getOriginalFilename();
        user.setImage(fileName);
        try {
            Files.copy(file.getInputStream(), Path.of(path).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("ОШИБКА ПРИ СОХРАНЕНИИ КАРТИНКИ");
        }
    }
}
