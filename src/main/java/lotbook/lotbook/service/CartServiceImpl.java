package lotbook.lotbook.service;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.Cart;
import lotbook.lotbook.dto.response.CartProduct;
import lotbook.lotbook.repository.CartMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;

    @Override
    public int register(Cart cart) {
        int result = 0;

        try {
            int stock = cartMapper.checkProductStock(cart);

            if (stock < cart.getCount()) {
                throw new Exception("장바구니 담기 에러 - 재고 부족");
            }

            int dupSeq = cartMapper.checkDuplicatedProduct(cart);
            if (dupSeq == 0) {
                result = cartMapper.addToCart(cart);
            } else if (dupSeq >= 1) {
                cart.setSequence(dupSeq);
                result = cartMapper.updateDuplicatedProduct(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int modify(Cart cart) {
        int result = 0;

        try {
            int stock = cartMapper.checkProductStock(cart);

            if (stock < cart.getCount()) {
                throw new Exception("장바구니 담기 에러 - 재고 부족");
            }

            result = cartMapper.updateCartProductCount(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int remove(Cart cart) {
        int result = 0;

        try {
            result = cartMapper.updateCartState(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Cart get(Cart cart) throws Exception {
        Cart cartResult = null;

        try {
            cartResult = cartMapper.selectCartDetail(cart);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("장바구니 단건 조회 에러");
        }

        return cartResult;
    }

    @Override
    public CartProduct cartGet(Cart cart) throws Exception {
        CartProduct cartProduct = null;

        try {
            cartProduct = cartMapper.selectedCartProductInfo(cart);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("장바구니 상품 정보 조회 에러");
        }

        return cartProduct;
    }

    @Override
    public int getCartCount(long sequence) throws Exception {
        int count = 0;
        try {
            count = cartMapper.getCartCount(sequence);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("장바구니 개수 조회 에러");
        }
        return count;
    }

    @Override
    public List<Cart> getAll(Cart cart) throws Exception {
        List<Cart> cartList = new ArrayList<>();
        try {
            cartList = cartMapper.selectCartAll(cart);

        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("장바구니 전체 조회 에러");
        }
        return cartList;
    }

    @Override
    public List<CartProduct> getProductInfo(Cart cart) throws Exception {
        List<CartProduct> product = null;
        try {
            product = cartMapper.selectProductInfo(cart);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("장바구니 상품 정보 조회 에러");
        }
        return product;
    }
}