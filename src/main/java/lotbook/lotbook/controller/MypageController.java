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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final CartService cartService;

    @GetMapping
    public String myPage(Model model, @RequestParam long memberSeq, HttpServletRequest request) {
        model.addAttribute("center", "mypage");
        HttpSession session = request.getSession();

        List<Cart> cartList = new ArrayList<>();
        List<CartProduct> productList = new ArrayList<>();
        request.setAttribute("myCartList", null);
        request.setAttribute("myCartProductList", null);

        Cart cart = Cart.builder().memberSequence(memberSeq).build();

        try {
            cartList = cartService.getAll(cart);
            request.setAttribute("myCartList", cartList);
            productList = cartService.getProductInfo(cart);
            request.setAttribute("myCartProductList", productList);

            int cartCount = cartService.getCartCount(memberSeq);
            session.setAttribute("cartCount", cartCount);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "index";
    }
}
