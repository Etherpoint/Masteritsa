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

    /**Эндпоинт на главную страницу веб-приложения
     *
     * @param model
     * @return file index.html
     */
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    /**Эндпоинт перехода на страницу товара
     *
     * @param model
     * @return file product.html
     */
    @GetMapping("/product")
    public String getProduct(Model model){
        return "product";
    }
}
