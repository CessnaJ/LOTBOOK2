package lotbook.lotbook.controller;

import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.response.CartProduct;
import lotbook.lotbook.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("checkout")
public class CheckoutController {


    private final ProductService productService;
//    private final CheckoutService checkoutService;

    @GetMapping(value = "/api/checkoutbuynow")
    public String CheckoutPage(Model model, @RequestParam long count, @RequestParam int productId, @RequestParam long memberSeq) {
        log.warn("제품 상세에서 바로 주문 페이지");


        model.addAttribute("center", "checkoutbuynow");

        List<CartProduct> productList = new ArrayList<>();
        try {
            Product res = productService.get(productId);
            productList.add(new CartProduct());
            productList.get(0).setName(res.getName());
            productList.get(0).setPrice(res.getPrice());
            productList.get(0).setDiscountRate(res.getDiscountRate());
            productList.get(0).setTotalPoint((int) (Math
                    .floor(res.getPrice() * count * res.getPointAccumulationRate() * 0.01)));
            productList.get(0).setCount((int) count);
            int priceMuldiscountRate = (int) ((int) ((int) res.getPrice() * ((100 - res.getDiscountRate()) * 0.01))
                                * count);

            productList.get(0).setTotalPrice(priceMuldiscountRate - priceMuldiscountRate % 10);
            model.addAttribute("res", res);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        model.addAttribute("orderProductList", productList);
        model.addAttribute("count", count);
        model.addAttribute("productId", productId);

        return "index";
    }
}
