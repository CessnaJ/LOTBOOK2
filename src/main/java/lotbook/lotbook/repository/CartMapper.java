package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Cart;
import lotbook.lotbook.dto.response.CartProduct;

import java.util.List;

public interface CartMapper {
    int getCartCount(long sequence);
    List<CartProduct> selectProductInfo(Cart cart);
    int checkProductStock(Cart cart);
    int checkDuplicatedProduct(Cart cart);
    int updateDuplicatedProduct(Cart cart);
    int addToCart(Cart cart);
    List<Cart> selectCartAll(Cart cart);
    Cart selectCartDetail(Cart cart);
    int updateCartProductCount(Cart cart);
    int updateCartState(Cart cart);
    CartProduct selectedCartProductInfo(Cart cart);
    int cartToOrder(Cart cart);
    int updateProductInfo(Cart cart);
}
