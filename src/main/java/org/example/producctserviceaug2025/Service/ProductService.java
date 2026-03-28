package org.example.producctserviceaug2025.Service;

import org.example.producctserviceaug2025.Models.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getSingleProduct(Long id);

    public Product createProduct(Product product);

    public void deleteProduct(Long id);

}
