package lotbook.lotbook.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.response.SearchResult;
import lotbook.lotbook.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public String searchResultByKeyword(@RequestParam String keyword,
                                        @RequestParam(required = false) String orderby,
                                        @RequestParam(required = false) String category, Model model) {
        model.addAttribute("center", "search");
        log.warn("/search 입장" + "키워드는" + keyword + ", 정렬기준은 " + orderby + ", 카테고리는" + category);

        if (orderby == null) {
            orderby = "popular";
        }

        try {
            SearchResult searchResult = searchService.getAllByKeyword(keyword, orderby, category);
            model.addAttribute("searchResult", searchResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "index";
    }

}
