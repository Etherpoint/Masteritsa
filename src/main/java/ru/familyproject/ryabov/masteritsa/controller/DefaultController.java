package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import java.util.List;

@Controller
public class DefaultController {

    /**
     * Сервис для работы с БД
     */
    private final ProductTypeService productTypeService;

    public DefaultController(@Autowired ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    /**
     * GET метод к главной странице сайта по эндпоинту <b>"/"</b>
     *
     * @param model
     * @return file <b>index.html</b>
     * @see Endpoints#MAIN_PAGE
     * @see #productTypeService
     */
    @GetMapping(Endpoints.MAIN_PAGE)
    public String index(Model model) {
        List<ProductType> types = productTypeService.getAll();
        model.addAttribute("types", types);
        return "main";
    }

    /**
     * GET метод к странице товара по эндпоинту <b>"/product"</b>
     *
     * @param model
     * @return file <b>product.html</b>
     * @see Endpoints#PRODUCT
     */
    @GetMapping(Endpoints.PRODUCT)
    public String getProduct(Model model) {
        return "product";
    }
}
