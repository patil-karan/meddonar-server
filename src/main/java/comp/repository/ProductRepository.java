package comp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import comp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p " +
           "WHERE (:category = '' OR p.category.categoryName = :category)")
    List<Product> filterProducts(@Param("category") String category);
}

