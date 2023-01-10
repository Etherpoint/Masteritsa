package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.service.UserService;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import java.util.List;

/**
 * EN:Controller for filtering goods<br>
 * For all methods of this controller, the initial postscript <b>"/products"</b> is added to the endpoint<br>
 * <P></P>
 * RU:Контроллер для фильтрации товаров<br>
 * Для всех методов этого контроллера добавляется начальная приписка <b>"/products"</b> к эндпоинту
 * @see Endpoints#FILTER
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Controller
@RequestMapping(Endpoints.FILTER)
public class FilterController {
    /**
     * EN: Service for working with entities <b>Product</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>Product</b> в БД
     */
    private final ProductService service;
    /**
     * EN: Service for working with entities <b>ProductType</b> in the database<br>
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
    public FilterController(@Autowired ProductService service,
                            @Autowired ProductTypeService productTypeService,
                            @Autowired UserService userService) {
        this.service = service;
        this.productTypeService = productTypeService;
        this.userService = userService;
    }

    /**
     * EN: GET method for loading all products by endpoint <b>"/all"</b><br>
     * RU: GET метод для загрузки всех товаров по эндпоинту <b>"/all"</b>
     * @return file <b>products.html</b>
     * @see Endpoints#FILTER_ALL
     */
    @GetMapping(Endpoints.FILTER_ALL)
    public String getAllProducts(Model model,@AuthenticationPrincipal UserDetails user) {
        List<Product> products = service.getAll();
        User entityUser = null;
        if (user != null){
            entityUser = (User) userService.loadUserByUsername(user.getUsername());
        }
        model.addAttribute("products", products);
        List<ProductType> types = productTypeService.getAll();
        model.addAttribute("types", types);
        model.addAttribute("user", entityUser);
        return "products";
    }

    /**
     * EN: GET method for loading a certain type of product by endpoint <b>"products/{id}"</b><br>
     * RU: GET метод для загрузки определенного типа товара по эндпоинту <b>"products/{id}"</b>
     * @return file <b>products.html</b>
     * @see Endpoints#FILTER
     * @see Endpoints#FIND_BY_ID
     */
    @GetMapping(Endpoints.FIND_BY_ID)
    public String getAllProductsById(Model model,@PathVariable Long id, @AuthenticationPrincipal UserDetails user){
        List<Product> products = service.getAllById(id);
        User entityUser = null;
        if (user != null){
            entityUser = (User) userService.loadUserByUsername(user.getUsername());
        }
        model.addAttribute("products", products);
        List<ProductType> types = productTypeService.getAll();
        model.addAttribute("types", types);
        model.addAttribute("user", entityUser);
        return "products";
    }
}
