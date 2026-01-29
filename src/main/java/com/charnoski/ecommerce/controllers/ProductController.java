package com.charnoski.ecommerce.controllers;

import com.charnoski.ecommerce.dto.ProductDTO;
import com.charnoski.ecommerce.entities.Product;
import com.charnoski.ecommerce.repositories.ProductRepository;
import com.charnoski.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value =  "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }
}
