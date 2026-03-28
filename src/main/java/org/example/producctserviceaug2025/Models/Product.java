package org.example.producctserviceaug2025.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product extends BaseModel {
    private String Title;
    private double Price;
    private String Description;
    @ManyToOne
    private Category category;
    private String imageUrl;
}
