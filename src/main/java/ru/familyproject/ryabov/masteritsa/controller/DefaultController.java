package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import java.util.List;

/**
 * EN: Controller for displaying the main page of the site <br>
 * RU: Контроллер для отображения главной страницы сайта
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Controller
public class DefaultController {

    /**
     * EN: Service for working with <b>ProductType</b> entities in the database<br>
     * RU: Сервис для работы с сущностями <b>ProductType</b> в БД
     */
    private final ProductTypeService productTypeService;

    /**
     * EN: Service initialization constructor<br>
     * RU: Конструктор для инициализации сервиса
     */
    public DefaultController(@Autowired ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    /**
     * EN: GET method to the main page of the site by endpoint <b>"/"</b><br>
     * RU: GET метод к главной странице сайта по эндпоинту <b>"/"</b>
     *
     * @return file <b>main.html</b>
     * @see Endpoints#MAIN_PAGE
     * @see #productTypeService
     */
    @GetMapping(Endpoints.MAIN_PAGE)
    public String index(Model model, @AuthenticationPrincipal User user) {
        List<ProductType> types = productTypeService.getAll();
        model.addAttribute("user", user);
        model.addAttribute("types", types);
        return "main";
    }
}
