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

@Controller
@RequestMapping(Endpoints.PRODUCT)
public class ProductController {
    private final ProductService productService;
    private final ProductTypeService productTypeService;

    public ProductController(@Autowired ProductService productService, @Autowired ProductTypeService productTypeService) {
        this.productService = productService;
        this.productTypeService = productTypeService;
    }

    /**
     * GET метод к странице товара по эндпоинту <b>"/product/{id}"</b>
     * @return file <b>product.html</b>
     * @see Endpoints#PRODUCT
     */
    @GetMapping(Endpoints.FILTER_BY_ID)
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
