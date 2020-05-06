package pl.haladyj.SpringSecurity_CH5_WebApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.haladyj.SpringSecurity_CH5_WebApplication.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
