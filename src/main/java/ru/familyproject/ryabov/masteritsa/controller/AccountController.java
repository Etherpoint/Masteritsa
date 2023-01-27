package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.exceptions.AccountException;
import ru.familyproject.ryabov.masteritsa.service.UserService;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

/**
 * EN: Controller for displaying a page with information about the user<br>
 * RU: Контроллер для отображения страницы с информацией о пользователе
 *
 * @author Danila Ryabov
 * @version 1.0
 */
@Controller
@RequestMapping(Endpoints.ACCOUNT)
public class AccountController {
    /**
     * EN: Service for working with Entities <b>User</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>User</b> в БД
     */
    private final UserService userService;

    /**
     * EN: Service initialization constructor<br>
     * RU: Конструктор для инициализации сервиса
     */
    public AccountController(@Autowired UserService userService) {
        this.userService = userService;
    }

    /**
     * EN: Controller for displaying a page with information about the user<br>
     * RU: GET метод к странице с информацией о пользователе
     *
     * @return file account.html
     */
    @GetMapping(Endpoints.FIND_BY_ID)
    public String getAccount(@AuthenticationPrincipal UserDetails user, @PathVariable Long id, Model model) {
        User entityUser = userService.loadUserByUsername(user.getUsername());
        if (!entityUser.getId().equals(id)) {
            throw new AccountException("ID АККАУНТА НЕ СОВПАДАЕТ С ЭНДПОИНТОМ");
        }
        model.addAttribute("user", entityUser);
        return "account";
    }
}
