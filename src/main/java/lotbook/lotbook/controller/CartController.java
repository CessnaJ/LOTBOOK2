package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Cart;
import lotbook.lotbook.dto.response.CartProduct;
import lotbook.lotbook.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping(value = "/api/shopingCart")
    public String ShopingCartPage(Model model, @RequestParam long memberSeq) {
        log.warn("장바구니 상세 페이지");
        model.addAttribute("center", "shoping-cart");

        List<Cart> cartList = new ArrayList<>();
        List<CartProduct> productList = new ArrayList<>();
        model.addAttribute("myCartList", null);
        model.addAttribute("myCartProductList", null);

        Cart cart = Cart.builder().memberSequence(memberSeq).build();

        try {
            cartList = cartService.getAll(cart);
            model.addAttribute("myCartList", cartList);
            productList = cartService.getProductInfo(cart);
            model.addAttribute("myCartProductList", productList);

            int cartCount = cartService.getCartCount(memberSeq);
            model.addAttribute("cartCount", cartCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }

    @GetMapping(value = "/api/deleteCart")
    public String DeleteCart(Model model, @RequestParam long sequence, @RequestParam long memberSeq, @RequestParam int isCart) {

        if (isCart == 0) {
            model.addAttribute("center", "mypage");
        } else {
            model.addAttribute("center", "shoping-cart");
        }

        List<Cart> cartList = new ArrayList<>();
        List<CartProduct> productList = new ArrayList<>();

        Cart cart = Cart.builder().sequence(sequence).memberSequence(memberSeq).build();

        try {
            int result = cartService.remove(cart);
            cartList = cartService.getAll(cart);
            model.addAttribute("myCartList", cartList);

            productList = cartService.getProductInfo(cart);
            model.addAttribute("myCartProductList", productList);

            int cartCount = cartService.getCartCount(memberSeq);
            model.addAttribute("cartCount", cartCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}
