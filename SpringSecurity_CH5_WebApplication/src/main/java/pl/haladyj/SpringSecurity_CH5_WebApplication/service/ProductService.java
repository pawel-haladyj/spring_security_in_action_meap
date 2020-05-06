package pl.haladyj.SpringSecurity_CH5_WebApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.haladyj.SpringSecurity_CH5_WebApplication.entity.Product;
import pl.haladyj.SpringSecurity_CH5_WebApplication.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
}
