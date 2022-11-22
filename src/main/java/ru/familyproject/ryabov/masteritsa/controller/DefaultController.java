package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepository;

@Controller
public class DefaultController {
    @Autowired
    ProductTypeRepository productTypeRepository;

    /**GET метод к главной странице сайта по эндпоинту <b>"/"</b>
     *
     * @param model
     * @return file <b>index.html</b>
     */
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    /**GET метод к странице товара по эндпоинту <b>"/product"</b>
     *
     * @param model
     * @return file <b>product.html</b>
     */
    @GetMapping("/product")
    public String getProduct(Model model){
        return "product";
    }
}
