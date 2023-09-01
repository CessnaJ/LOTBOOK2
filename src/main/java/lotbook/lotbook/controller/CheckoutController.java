package lotbook.lotbook.controller;

import lotbook.lotbook.dto.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("checkout")
public class CheckoutController {

//    private final CheckoutService checkoutService;

    @GetMapping(value = "/api/checkoutbuynow")
    public String CheckoutPage(Model model, @RequestParam long count, @RequestParam int productId, @RequestParam long memberSeq) {
        log.warn("제품 상세에서 바로 주문 페이지");


        model.addAttribute("center", "checkoutbuynow");


        Product product = Product.builder().sequence(productId).build();

//        model.addAttribute("orderProductList", productList);
        model.addAttribute("count", count);
        model.addAttribute("productId", productId);

        return "index";
    }
}
