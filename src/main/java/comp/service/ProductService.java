package comp.service;


	import java.util.List;

	import org.springframework.data.domain.Page;

import comp.exception.ProductException;
import comp.model.Product;
import comp.request.ProductRequest;


	public interface ProductService {
		
		public Product createProduct(ProductRequest req)throws ProductException;
		public String deleteProduct(Long productId) throws ProductException;
		public Product updateProduct(Long productId,Product product) throws ProductException;
		public Product findProductById(Long productId) throws ProductException;
		public List<Product> findProductByCategory(String category);
		public Page<Product> getAllProduct(String category, List<Integer> power,String stock,Integer pageNumber,Integer pageSize);
		
		public List<Product> findAllProducts();

	}
