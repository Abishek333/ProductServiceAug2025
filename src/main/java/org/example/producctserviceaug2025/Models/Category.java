package org.example.producctserviceaug2025.Models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@Entity(name = "categories")
public class Category extends BaseModel {
    private String title;

}
