package lotbook.lotbook.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import lotbook.lotbook.repository.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    //    private final SearchServiceImpl searchService;
    private final MemberMapper memberMapper; // 생성자 주입방식. 롬복의 RequiredArgsConstructor 덕분에 생성자 주입이 자동으로 이뤄짐

    @GetMapping
    public String search(@RequestParam String keyword,
                         @RequestParam String orderby,
                         @RequestParam String category,
                         Model model) {
        // 검색 로직 구현부분.. 간이로 이렇게 받아서 쓸 수 있다는걸 보여드립니다.
        model.addAttribute("resultsllllll", "Search results for: " + keyword + orderby);
        model.addAttribute("center", "search");
        log.warn("나왔나봅시다");
        log.warn("나왔나?" + memberMapper.selectMemberBySequence(2).toString());
        // Spring MVC를 의존성으로 걸어둬서, request.setAttribute 말고 이런식으로 줄 수 있음.

//        try {
//            searchService.getProductsByKeyword(keyword, orderby, category);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("들어왔어요.");
        return "main";  // search.jsp 페이지로 이동시킴.
    }




}
