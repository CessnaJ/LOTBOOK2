package lotbook.lotbook.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.response.ProductDetailWithReviews;
import lotbook.lotbook.repository.MemberMapper;
import lotbook.lotbook.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product-detail")
public class ProductDetailController {

    private final ProductService productService;

    @GetMapping("/{sequence}")
    public String getproductDetail(@PathVariable("sequence") long sequence, Model model) {
        model.addAttribute("center", "shop-details");
        log.warn("상품상세요청");
        ProductDetailWithReviews productDetailWithReviews = productService.getProductDetail(sequence);
        model.addAttribute("productDetail", productDetailWithReviews);
        // Spring MVC를 의존성으로 걸어둬서, request.setAttribute 말고 이런식으로 줄 수 있음.

        log.info("/product-detail 페이지로 보냅니다.");
        return "index";  // search.jsp 페이지로 이동시킴.
    }


}
