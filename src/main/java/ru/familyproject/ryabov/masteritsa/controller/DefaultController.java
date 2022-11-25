package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepository;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

@Controller
public class DefaultController {
    @Autowired
    ProductTypeRepository productTypeRepository;

    /**GET метод к главной странице сайта по эндпоинту <b>"/"</b>
     *
     * @param model
     * @see Endpoints#MAIN_PAGE
     * @return file <b>index.html</b>
     */
    @GetMapping(Endpoints.MAIN_PAGE)
    public String index(Model model){
        Iterable<ProductType> types = productTypeRepository.findAll();
        model.addAttribute("types", types);
        return "index";
    }

    /**GET метод к странице товара по эндпоинту <b>"/product"</b>
     *
     * @param model
     * @see Endpoints#PRODUCT
     * @return file <b>product.html</b>
     */
    @GetMapping(Endpoints.PRODUCT)
    public String getProduct(Model model){
        return "product";
    }
}
