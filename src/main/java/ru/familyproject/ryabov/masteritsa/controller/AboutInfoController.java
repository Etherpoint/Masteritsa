package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.UserService;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

/**
 * EN: Controller for displaying pages with information about the site<br>
 * RU: Контроллер для отображения страницы с информацией о сайте
 * @author Danila Ryabov
 * @version 1.0
 */
@Controller
public class AboutInfoController {
    /**
     * EN: Service for working with Entities <b>User</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>User</b> в БД
     */
    private final UserService userService;

    /**
     * EN: Service initialization constructor<br>
     * RU: Конструктор для инициализации сервиса
     */
    public AboutInfoController(@Autowired UserService userService){
        this.userService = userService;
    }
    /**
     * EN: GET method to the page with information about the site<br>
     * RU: GET метод к странице с информацией о сайте
     * @return file about.html
     */
    @GetMapping(Endpoints.ABOUT)
    public String getInfo(Model model, @AuthenticationPrincipal UserDetails user){
        User entityUser = null;
        if (user != null){
            entityUser = (User) userService.loadUserByUsername(user.getUsername());
        }
        model.addAttribute("user", entityUser);
        return "about";
    }
}
