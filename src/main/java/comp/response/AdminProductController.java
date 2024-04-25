package comp.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comp.exception.ProductException;
import comp.model.Product;
import comp.request.ProductRequest;
import comp.service.ProductService;

@RestController
	@RequestMapping("/api/admin/product")
	public class AdminProductController {

		private ProductService productService;

		public AdminProductController(ProductService productService) {
			super();
			this.productService = productService;
		}
		
		
		@PostMapping("/")
		public ResponseEntity<Product> createProduct(@RequestBody ProductRequest req)throws ProductException{
			
			Product product = productService.createProduct(req);
			
			return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
		}
		
		@DeleteMapping("/{productId}/delete")
		public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException{
			
			productService.deleteProduct(productId);
			
			ApiResponse response = new ApiResponse();
			response.setMessage("Deleted Successfully");
			response.setStatus(true);
			
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
		}
		
		@GetMapping("/all")
		public ResponseEntity<List<Product>> findAllProducts(){
			List<Product> products = productService.findAllProducts();
			return new ResponseEntity<>(products,HttpStatus.OK);
		}
		
		@PutMapping("{productId}/product")
		public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product req) throws ProductException{
			Product product = productService.updateProduct(productId, req);
			return new ResponseEntity<Product>(product,HttpStatus.CREATED);
			
		}
		
		public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody ProductRequest[] req) throws ProductException{
			for(ProductRequest product : req) {
				productService.createProduct(product);
			}
			
			ApiResponse response = new ApiResponse();
			response.setMessage("Product Create");
			response.setStatus(true);
			
			return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
		}

}
