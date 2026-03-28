package org.example.producctserviceaug2025.Controllers;

import org.example.producctserviceaug2025.Models.Category;
import org.example.producctserviceaug2025.Models.Product;
import org.example.producctserviceaug2025.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
       return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName){
        return ResponseEntity.ok(categoryService.getProducts(categoryName));
    }
}
