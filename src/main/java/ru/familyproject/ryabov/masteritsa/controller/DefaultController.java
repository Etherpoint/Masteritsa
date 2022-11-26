package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.repository.ProductRepository;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepository;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DefaultController {
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductRepository productRepository;

    /**GET метод к главной странице сайта по эндпоинту <b>"/"</b>
     *
     * @param model
     * @see Endpoints#MAIN_PAGE
     * @return file <b>index.html</b>
     */
    @GetMapping(Endpoints.MAIN_PAGE)
    public String index(Model model){
        Iterable<ProductType> types = productTypeRepository.findAll();
        Map<ProductType, List<Product>> map = new HashMap<>();
        types.forEach(type -> map.put(type, productRepository.findByProductType(type)));
        model.addAttribute("map", map);
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
