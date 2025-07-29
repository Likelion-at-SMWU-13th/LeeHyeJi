package org.example.seminar2.controller;

import org.example.seminar2.model.Product;
import org.example.seminar2.service.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String viewProducts(Model model) {
        var products = productService.findAll();
        model.addAttribute("products", products);

        return "products.html";

    }

    @RequestMapping(path="/products", method = RequestMethod.POST)
    public String addProduct(
            @RequestParam String name,
                             @RequestParam double price,
                             Model model
    ) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        productService.addProduct(p);

        var products = productService.findAll();
        model.addAttribute("products", products);

        return "products.html";

    }
}
