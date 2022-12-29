package ru.familyproject.ryabov.masteritsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

/**
 * EN: Controller for displaying the promotions page<br>
 * RU: Контроллер для отображения страницы акций
 * @author Danila Ryabov
 * @version 1.0
 */
@Controller
public class SaleController {
    /**
     * EN: GET method to the page with promotions<br>
     * RU: GET метод к странице с акциями
     * @return file sales.html
     */
    @GetMapping(Endpoints.SALES)
    public String getSales(){
        return "sales";
    }
}
