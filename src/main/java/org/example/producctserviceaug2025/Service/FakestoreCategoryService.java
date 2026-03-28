package org.example.producctserviceaug2025.Service;

import org.example.producctserviceaug2025.Models.Category;
import org.example.producctserviceaug2025.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FakestoreCategoryService implements CategoryService {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    @Override
    public List<Category> getCategories() {
        String url= "https://fakestoreapi.com/products/categories";
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> response = restTemplate.getForEntity(url, String[].class);
        ArrayList<Category> categoryList= new ArrayList<>();

        for(String categoryName : response.getBody()){
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setId(ThreadLocalRandom.current().nextLong(1, Integer.MAX_VALUE));
            categoryList.add(category);
        }
        return categoryList;
    }

    @Override
    public List<Product> getProducts(String categoryName) {
        String url= "https://fakestoreapi.com/products/products/{"+categoryName+"}";
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<Product[]> products= restTemplate.getForEntity(url, Product[].class);
        Product[] productsArray= products.getBody();

        ArrayList<Product> productsList= new ArrayList<>();

        if(productsArray!=null){
            productsList.addAll(Arrays.asList(productsArray));
        }
        return productsList;
    }
}
