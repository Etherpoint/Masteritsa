package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import java.util.List;

/**
 * Контроллер для фильтрации товаров<br>
 * Для всех методов этого контроллера добавляется начальная приписка <b>"/products"</b> к эндпоинту
 * @see Endpoints#FILTER
 */
@Controller
@RequestMapping(Endpoints.FILTER)
public class FilterController {
    /**
     * Сервис для работы с сущностями <b>Product</b> в БД
     */
    private final ProductService service;
    /**
     * Сервис для работы с сущностями <b>ProductType</b> в БД
     */
    private final ProductTypeService productTypeService;

    /**
     * Конструктор для инициализации сервисов
     *
     * @param service
     * @param productTypeService
     */
    public FilterController(@Autowired ProductService service, @Autowired ProductTypeService productTypeService) {
        this.service = service;
        this.productTypeService = productTypeService;
    }

    /**
     * GET метод для загрузки всех товаров по эндпоинту <b>"/all"</b>
     * @return file <b>products.html</b>
     * @see Endpoints#FILTER_ALL
     */
    @GetMapping(Endpoints.FILTER_ALL)
    public String getAllProducts(Model model) {
        List<Product> products = service.getAll();
        model.addAttribute("products", products);
        List<ProductType> types = productTypeService.getAll();
        model.addAttribute("types", types);
        return "products";
    }

    /**
     * GET метод для загрузки определенного типа товара по эндпоинту <b>"products/{id}"</b>
     * @return file <b>products.html</b>
     * @see Endpoints#FILTER
     * @see Endpoints#FIND_BY_ID
     */
    @GetMapping(Endpoints.FIND_BY_ID)
    public String getAllProductsById(Model model,@PathVariable Long id){
        List<Product> products = service.getAllById(id);
        model.addAttribute("products", products);
        List<ProductType> types = productTypeService.getAll();
        model.addAttribute("types", types);
        return "products";
    }
}
