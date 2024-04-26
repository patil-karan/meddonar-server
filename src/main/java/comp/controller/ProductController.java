package comp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comp.exception.ProductException;
import comp.model.Product;
import comp.service.ProductService;

@RestController
	@RequestMapping("products")
	public class ProductController {

		@Autowired
		private ProductService productService;
		
		@GetMapping("/byCategory")
		public ResponseEntity<Page<Product>> findProductByCategory(@RequestParam String category,
				@RequestParam List<Integer> power,@RequestParam String stock,@RequestParam Integer pagenumber,@RequestParam Integer pageSize){
			
			Page<Product> productPage = productService.getAllProduct(category, power, stock, pagenumber, pageSize);
			
			return new ResponseEntity<Page<Product>>(productPage,HttpStatus.ACCEPTED);
		}
		
		@GetMapping("/id/{productId}")
		public ResponseEntity<Product> findProductById(@PathVariable Long productId)throws ProductException{
			
			Product product = productService.findProductById(productId);
			
			return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
		}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProducts(){
		List<Product> products = productService.findAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
}
