package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     * EN: GET method to the page with information about the site<br>
     * RU: GET метод к странице с информацией о сайте
     * @return file about.html
     */
    @GetMapping(Endpoints.ABOUT)
    public String getInfo(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        return "about";
    }
}
