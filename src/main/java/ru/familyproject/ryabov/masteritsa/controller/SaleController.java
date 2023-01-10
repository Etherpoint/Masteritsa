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
 * EN: Controller for displaying the promotions page<br>
 * RU: Контроллер для отображения страницы акций
 * @author Danila Ryabov
 * @version 1.0
 */
@Controller
public class SaleController {
    /**
     * EN: Service for working with Entities <b>User</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>User</b> в БД
     */
    private final UserService userService;

    /**
     * EN: Service initialization constructor<br>
     * RU: Конструктор для инициализации сервиса
     */
    public SaleController(@Autowired UserService userService) {
        this.userService = userService;
    }

    /**
     * EN: GET method to the page with promotions<br>
     * RU: GET метод к странице с акциями
     * @return file sales.html
     */
    @GetMapping(Endpoints.SALES)
    public String getSales(Model model, @AuthenticationPrincipal UserDetails user){
        User entityUser = null;
        if (user != null){
            entityUser = (User) userService.loadUserByUsername(user.getUsername());
        }
        model.addAttribute("user", entityUser);
        return "sales";
    }
}
