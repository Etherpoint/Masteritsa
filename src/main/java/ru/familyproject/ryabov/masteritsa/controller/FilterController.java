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

@Controller
@RequestMapping(Endpoints.FILTER)
public class FilterController {
    private final ProductService service;

    public FilterController(@Autowired ProductService service, @Autowired ProductTypeService productTypeService) {
        this.service = service;
        this.productTypeService = productTypeService;
    }

    private final ProductTypeService productTypeService;


    @GetMapping(Endpoints.FILTER_ALL)
    public String getAllProducts(Model model) {
        List<Product> products = service.getAll();
        model.addAttribute("products", products);
        List<ProductType> types = productTypeService.getAll();
        model.addAttribute("types", types);
        return "products";
    }

    @GetMapping(Endpoints.FILTER_BY_ID)
    public String getAllProductsById(Model model,@PathVariable Long id){
        List<Product> products = service.getAllById(id);
        model.addAttribute("products", products);
        List<ProductType> types = productTypeService.getAll();
        model.addAttribute("types", types);
        return "products";
    }
}
