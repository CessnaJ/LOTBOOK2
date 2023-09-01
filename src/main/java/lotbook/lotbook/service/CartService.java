package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.Cart;
import lotbook.lotbook.dto.response.CartProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    int register(Cart cart);
    int modify(Cart cart);
    int remove(Cart cart);
    Cart get(Cart cart) throws Exception;
    CartProduct cartGet(Cart cart) throws Exception;
    int getCartCount(long sequence) throws Exception;
    List<Cart> getAll(Cart cart) throws Exception;
    List<CartProduct> getProductInfo(Cart cart) throws Exception;
}
