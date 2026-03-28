package org.example.producctserviceaug2025.Service;

import org.example.producctserviceaug2025.DTO.FakestoreProductDTO;
import org.example.producctserviceaug2025.Exceptions.ProductNotFoundExceptions;
import org.example.producctserviceaug2025.Models.Category;
import org.example.producctserviceaug2025.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FakestoreProductService implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;

    public FakestoreProductService(RestTemplate restTemplate, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplate;
        this.restTemplateBuilder = restTemplateBuilder;
    }


    @Override
    public List<Product> getAllProducts() {

//        FakestoreProductDTO[] fakestoreProductDTO= new FakestoreProductDTO[];
        ResponseEntity<FakestoreProductDTO[]> fakestoreProductDTO= restTemplate.getForEntity("https://fakestoreapi.com/products/", FakestoreProductDTO[].class);
        FakestoreProductDTO[] products= fakestoreProductDTO.getBody();

        List<Product> productList=new ArrayList<>();
        for(FakestoreProductDTO fakestoreProductDTO1:products){
            productList.add(convertFakestoreDtotoProduct(fakestoreProductDTO1));

        }

        return productList;
    }

    @Override
    public Product getSingleProduct(Long id) {
        ResponseEntity<FakestoreProductDTO> fakestoreProductDTOResponseEntity= restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                FakestoreProductDTO.class);

//        assert fakestoreProductDTOResponseEntity.getBody() != null;
        if(!fakestoreProductDTOResponseEntity.getStatusCode().isError() || fakestoreProductDTOResponseEntity.getBody()!=null){
            return convertFakestoreDtotoProduct(fakestoreProductDTOResponseEntity.getBody());
        }else{
            throw new ProductNotFoundExceptions(id);
        }
    }

    @Override
    public Product createProduct(Product product) {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products/", product, String.class);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/" + id, Void.class);
    }

    public Product convertFakestoreDtotoProduct(FakestoreProductDTO fakestoreProductDTO) {
        Product product = new Product();
        product.setDescription(fakestoreProductDTO.getDescription());
        product.setPrice(fakestoreProductDTO.getPrice());
        product.setTitle(fakestoreProductDTO.getTitle());
        product.setImageUrl(fakestoreProductDTO.getImage());

        Category category = new Category();
        category.setCategoryName(fakestoreProductDTO.getCategory());
        product.setCategory(category);

        return product;
    }
}
