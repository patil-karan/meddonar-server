package comp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import comp.exception.ProductException;
import comp.model.Category;
import comp.model.Product;
import comp.repository.CategoryRepository;
import comp.repository.ProductRepository;
import comp.request.ProductRequest;



@Service
	public class ProductServiceImplementation implements ProductService{
		
	@Autowired
		private CategoryRepository categoryRepository;
	@Autowired
		private ProductRepository productRepository;
		
		
		public ProductServiceImplementation(CategoryRepository categoryRepository, ProductRepository productRepository) {
			this.categoryRepository = categoryRepository;
			this.productRepository = productRepository;
		}

		@Override
		public Product createProduct(ProductRequest req) throws ProductException {
		    Category category = categoryRepository.findByCategoryName(req.getCategoryName());
		    Product product = new Product();
		    Category newCategory;
		    if (category==null){
		        newCategory = new Category();
		        newCategory.setCategoryName(req.getCategoryName());
		        newCategory.setCreatedAt(LocalDateTime.now());
		        newCategory.setCategoryDescription("new Category "+req.getCategoryName());
		        newCategory.setUpdatedAt(LocalDateTime.now());
		        product.setCategory(categoryRepository.save(newCategory));
		    }else {
		                product.setCategory(category);
		    }


		    product.setTitle(req.getTitle());
		    product.setDiscription(req.getDescription());
		    product.setBrand(req.getBrand());
		    product.setImageUrl(req.getImageUrl());
		    product.setPowers(req.getPower());
		    product.setQuantity(req.getQuantity());
		    product.setCreatedAt(LocalDateTime.now());

		    Product savedProduct = productRepository.save(product);
		    return savedProduct;
		}

		@Override
		public String deleteProduct(Long productId) throws ProductException {
			Product product = findProductById(productId);
			product.getPowers().clear();
			productRepository.delete(product);
			return "Product Deleted Successfully";
		}

		@Override
		public Product updateProduct(Long productId, Product product) throws ProductException {
		    Product existingProduct = productRepository.findById(productId).orElse(null);
		    if (existingProduct==null)
		        throw new ProductException("Product is not available for id "+productId);

		    Category category = categoryRepository.findByCategoryName(product.getCategory().getCategoryName());

		    if (category==null){
		        throw new ProductException(product.getCategory().getCategoryName()+" category is not available");
		    }

		    if (product.getQuantity()<0){
		        throw new ProductException("Quantity can not be negative");
		    }
		    existingProduct.setTitle(product.getTitle());
		    existingProduct.setDiscription(product.getDiscription());
		    existingProduct.setBrand(product.getBrand());
		    existingProduct.setImageUrl(product.getImageUrl());
		    existingProduct.setPowers(product.getPowers());
		    existingProduct.setQuantity(product.getQuantity());
		    existingProduct.setCategory(product.getCategory());
		    return productRepository.save(existingProduct);
		}

		@Override
		public Product findProductById(Long productId) throws ProductException {
			Optional<Product> optional = productRepository.findById(productId);
			
			if (optional.isPresent()) {
				return optional.get();
			}
			throw new ProductException("Product not found With id - "+productId );
		}

		@Override
		public List<Product> findProductByCategory(String category) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<Product> getAllProduct(String category, List<Integer> power, String stock, Integer pageNumber, Integer pageSize) {

		    // Create pageable
		    Pageable pageable = PageRequest.of(pageNumber, pageSize);
		    
		    // Fetch products based on category
		    List<Product> products = productRepository.filterProducts(category);
		    
		    // Apply power filter if power is not empty
		    if (!power.isEmpty()) {
		        products = products.stream()
		                .filter(p -> power.stream().anyMatch(pow -> p.getPowers().contains(pow)))
		                .collect(Collectors.toList());
		    }

		    // Apply stock filter if stock is not null
		    if (stock != null) {
		        if (stock.equals("in_stock")) {
		            products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
		        } else if (stock.equals("out_of_stock")) {
		            products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
		        }
		    }
		    
		    // Calculate start and end index for pagination
		    int startIdx = pageNumber * pageSize;
		    int endIdx = Math.min(startIdx + pageSize, products.size());
		    
		    // Get the sublist for the current page
		    List<Product> pageContent = products.subList(startIdx, endIdx);
		    
		    // Create Page object
		    Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
		    
		    return filteredProducts;
		}


		@Override
		public List<Product> findAllProducts() {
			return productRepository.findAll();
		}

	}

