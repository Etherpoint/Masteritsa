package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.service.UserService;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import java.util.List;

/**
 * EN: Controller for displaying the product page<br>
 * For all methods of this controller, the initial postscript <b>"/product"</b> is added<br>
 * <P></P>
 * RU: Констроллер для отображения страницы товара<br>
 * Для всех методов этого контроллера добавляется начальная приписка <b>"/product"</b>
 *
 * @author Danila Ryabov
 * @version 1.0
 * @see Endpoints#PRODUCT
 */
@Controller
@RequestMapping(Endpoints.PRODUCT)
public class ProductController {
    /**
     * EN: Service for working with entities <b>Product</b> in the database<br>
     * RU: Сервис для работы с сущностями <b>Product</b> в БД
     */
    private final ProductService productService;
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
    public ProductController(@Autowired ProductService productService,
                             @Autowired ProductTypeService productTypeService,
                             @Autowired UserService userService) {
        this.productService = productService;
        this.productTypeService = productTypeService;
        this.userService = userService;
    }

    /**
     * EN: GET method to the product page by endpoint <b>"/product/{id}"</b><br>
     * RU: GET метод к странице товара по эндпоинту <b>"/product/{id}"</b>
     *
     * @return file <b>product.html</b>
     * @see Endpoints#PRODUCT
     * @see Endpoints#FIND_BY_ID
     */
    @GetMapping(Endpoints.FIND_BY_ID)
    public String getProduct(Model model, @PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        User entityUser = null;
        if (user != null){
            entityUser = userService.loadUserByUsername(user.getUsername());
        }
        List<Comment> comments = productService.getAllCommentsById(id);
        List<ProductType> types = productTypeService.getAll();
        Product product = productService.getById(id);
        model.addAttribute("comments", comments);
        model.addAttribute("types", types);
        model.addAttribute("product", product);
        model.addAttribute("user", entityUser);
        return "product";
    }
}
