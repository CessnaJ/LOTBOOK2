package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.mapper.CategoryProductWithReviewDTO;
import lotbook.lotbook.dto.response.ProductDetailWithReviews;
import lotbook.lotbook.service.CartService;
import lotbook.lotbook.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final CartService cartService;
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping
    public String DefaultMainPage(Model model){//, HttpServletRequest request) {
        model.addAttribute("center", "main");
        List<CategoryProductWithReviewDTO> popularProducts = productService.getPopular();
        model.addAttribute("popularProducts", popularProducts);

        List<CategoryProductWithReviewDTO> LatestProducts = productService.getLatest();
        model.addAttribute("LatestProducts", LatestProducts);

        List<CategoryProductWithReviewDTO> DiscountrProducts = productService.getHighDiscount();
        model.addAttribute("DiscountrProducts", DiscountrProducts);

        List<CategoryProductWithReviewDTO> PointProducts = productService.getHighPoint();
        model.addAttribute("PointProducts", PointProducts);

        log.info("=============================");
        log.info(LatestProducts.toString());
        log.info(PointProducts.toString());
        log.info("=============================");
        /*Member mem = (Member) request.getAttribute("logincust");
        Long memberSeq = (long) 0;
        if (mem != null) Long.parseLong(String.valueOf(mem.getSequence()));

        int cartCount = 0;
        try {
            cartCount = cartService.getCartCount(memberSeq);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ProductDetailWithReviews productDetailWithReviews = productService.getProductDetail(sequence);


        request.setAttribute("cartCount", cartCount);*/
        return "index";
    }
}
