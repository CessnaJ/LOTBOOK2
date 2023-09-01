package lotbook.lotbook.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.repository.MemberMapper;
import lotbook.lotbook.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product-detail")
public class ProductDetailController {

    private final ProductService productService; // 생성자 주입방식. 롬복의 RequiredArgsConstructor 덕분에 생성자 주입이 자동으로 이뤄짐

    @GetMapping
    public String getproduct(Model model) {
        // 검색 로직 구현부분.. 간이로 이렇게 받아서 쓸 수 있다는걸 보여드립니다.
        model.addAttribute("data-test", "Search results for: " + "데이터가 잘 받아서 들어오나요" );
        model.addAttribute("center", "shop-details");

        log.warn("상품상세요청");
        Product product = productService.get(1);
        log.warn(product.toString());
        model.addAttribute("product", product);
        // Spring MVC를 의존성으로 걸어둬서, request.setAttribute 말고 이런식으로 줄 수 있음.

//        try {
//            searchService.getProductsByKeyword(keyword, orderby, category);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        log.info("/product-detail 로 들어왔어요 ㅋㅋ.");
        return "index";  // search.jsp 페이지로 이동시킴.
    }


}
