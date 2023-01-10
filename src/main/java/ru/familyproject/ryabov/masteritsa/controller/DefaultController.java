package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.service.UserService;
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
     * EN: Service for working with Entities <b>User</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>User</b> в БД
     */
    private final UserService userService;

    /**
     * EN: Services initialization constructor<br>
     * RU: Конструктор для инициализации сервисов
     */
    public DefaultController(@Autowired ProductTypeService productTypeService,
                             @Autowired UserService userService){
        this.productTypeService = productTypeService;
        this.userService = userService;
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
    public String index(Model model, @AuthenticationPrincipal UserDetails user) {
        List<ProductType> types = productTypeService.getAll();
        User entityUser = null;
        if (user != null){
            entityUser = (User) userService.loadUserByUsername(user.getUsername());
        }
        model.addAttribute("user", entityUser);
        model.addAttribute("types", types);
        return "main";
    }
}
