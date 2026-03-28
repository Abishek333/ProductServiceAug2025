package org.example.producctserviceaug2025.Service;

import org.example.producctserviceaug2025.Models.Category;
import org.example.producctserviceaug2025.Models.Product;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategories();

    public List<Product> getProducts(String categoryName);
}
