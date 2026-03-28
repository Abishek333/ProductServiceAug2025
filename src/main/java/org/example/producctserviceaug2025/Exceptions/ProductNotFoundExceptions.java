package org.example.producctserviceaug2025.Exceptions;

import org.example.producctserviceaug2025.Models.Product;

public class ProductNotFoundExceptions extends RuntimeException{
    private Long productId;

    public ProductNotFoundExceptions(Long productId) {
        this.productId = productId;
    }

    public ProductNotFoundExceptions(){

    }

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
