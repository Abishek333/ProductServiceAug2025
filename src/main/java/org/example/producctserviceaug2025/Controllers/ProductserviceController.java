package org.example.producctserviceaug2025.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.producctserviceaug2025.Commons.AuthCommons;
import org.example.producctserviceaug2025.Exceptions.UnauthorizedAccessException;
import org.example.producctserviceaug2025.Models.Product;
import org.example.producctserviceaug2025.Service.CategoryService;
import org.example.producctserviceaug2025.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/Products")
public class ProductserviceController{
    private final CategoryService categoryService;
    ProductService productService;

    public ProductserviceController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}/{token}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id,  @PathVariable("token") String token)  {

//        try{
//            return new ResponseEntity<>(productService.getSingleProduct(id),
//                    HttpStatus.OK);
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }

        //Product product= null;

        if(!AuthCommons.validateToken(token)) {
            throw new UnauthorizedAccessException("Unauthorized access: Invalid token");
        }
            Product product = productService.getSingleProduct(id);

            return ResponseEntity.ok(product);
            //return new ResponseEntity<>(product, HttpStatus.OK);


    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {

//         //String CategoryName= product.getCategory();
//         //categoryService.getCategories();

         return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return  ResponseEntity.ok().build();
    }
}
