package com.sst.productservicesst.controllers;

import com.sst.productservicesst.dtos.ExceptionDto;
import com.sst.productservicesst.models.Product;
import com.sst.productservicesst.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RuntimeException exception) {
            ExceptionDto dto = new ExceptionDto();
            dto.setMessage("Something went wrong");
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts () {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
}