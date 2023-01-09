package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.familyproject.ryabov.masteritsa.entity.User;

/**
 * EN: Controller for displaying a page with information about the user<br>
 * RU: Контроллер для отображения страницы с информацией о пользователе
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/account/")
public class AccountController {

    /**
     * EN: Controller for displaying a page with information about the user<br>
     * RU: GET метод к странице с информацией о пользователе
     *
     * @return file account.html
     */
    @GetMapping("{id}")
    public String getAccount(@AuthenticationPrincipal UserDetails user, @PathVariable Long id, Model model) {
        if (!((User) user).getId().equals(id)){
            throw new RuntimeException("ID АККАУНТА НЕ СОВПАДАЕТ С ЭНДПОИНТОМ");
        }
        model.addAttribute("user", user);
        return "account";
    }
}
