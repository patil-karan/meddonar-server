package comp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import comp.model.Category;



public interface CategoryRepository extends JpaRepository<Category, Long>{

	public Category findByCategoryName(String categoryName);
}