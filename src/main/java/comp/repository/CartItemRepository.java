package comp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import comp.model.Cart;
import comp.model.CartItems;
import comp.model.Product;

public interface CartItemRepository extends JpaRepository<CartItems, Long>{

		@Query("SELECT ci FROM CartItems ci WHERE ci.cart =:cart AND ci.product = :product AND ci.power = :power AND ci.userId = :userId")
		 CartItems isCartItemExist(@Param("cart") Cart cart,@Param("product") Product product,@Param("power") String power,
				@Param("userId")Long userId);
	}

