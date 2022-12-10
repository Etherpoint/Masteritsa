package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import java.util.List;

/**
 * Констроллер для отображения страницы товара<br>
 * Для всех методов этого контроллера добавляется начальная приписка <b>"/product"</b>
 * @see Endpoints#PRODUCT
 */
@Controller
@RequestMapping(Endpoints.PRODUCT)
public class ProductController {
    /**
     * Сервис для работы с сущностями <b>Product</b> в БД
     */
    private final ProductService productService;
    /**
     * Сервис для работы с сущностями <b>ProductType</b> в БД
     */
    private final ProductTypeService productTypeService;

    /**
     * Конструктор для инициализации сервисов
     * @param productService
     * @param productTypeService
     */
    public ProductController(@Autowired ProductService productService, @Autowired ProductTypeService productTypeService) {
        this.productService = productService;
        this.productTypeService = productTypeService;
    }

    /**
     * GET метод к странице товара по эндпоинту <b>"/product/{id}"</b>
     * @return file <b>product.html</b>
     * @see Endpoints#PRODUCT
     * @see Endpoints#FIND_BY_ID
     */
    @GetMapping(Endpoints.FIND_BY_ID)
    public String getProduct(Model model, @PathVariable Long id) {
        List<Comment> comments = productService.getAllCommentsById(id);
        List<ProductType> types = productTypeService.getAll();
        Product product = productService.getById(id);
        model.addAttribute("comments", comments);
        model.addAttribute("types", types);
        model.addAttribute("product", product);
        return "product";
    }
}
