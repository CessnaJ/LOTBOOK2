package lotbook.lotbook.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.CategoryProductWithReviewDTO;
import lotbook.lotbook.dto.response.ProductDetailWithReviews;
import lotbook.lotbook.dto.response.SearchResult;
import lotbook.lotbook.service.CategoryService;
import lotbook.lotbook.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService; // 생성자 주입방식. 롬복의 RequiredArgsConstructor 덕분에 생성자 주입이 자동으로 이뤄짐
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    public String searchResultByKeyword(@RequestParam String view,
                                        @RequestParam(required = false) String orderby,
                                         Model model) throws Exception {
        model.addAttribute("center", "category");
        if (orderby == null) {
            orderby = "popular";
        }

        if(view.equals("1") || view.equals("6") || view.equals("10")) {
            List<CategoryProductWithReviewDTO> categoryResult = categoryService.getBigCategory(view, orderby);
            model.addAttribute("categoryResult", categoryResult);
        } else {
            List<CategoryProductWithReviewDTO> categoryResult = categoryService.getSmallCategory(view, orderby);
            model.addAttribute("categoryResult", categoryResult);
        }
        return "index";  // search.jsp 페이지로 이동시킴.
    }


}
