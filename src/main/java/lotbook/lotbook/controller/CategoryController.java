package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {


    @GetMapping("/main")
    public String login(Model model) {
        // 검색 로직 구현부분.. 간이로 이렇게 받아서 쓸 수 있다는걸 보여드립니다.
        model.addAttribute("data-test", "Search results for: " + "로그인하려고?" );
        model.addAttribute("center", "signin");
        // Spring MVC를 의존성으로 걸어둬서, request.setAttribute 말고 이런식으로 줄 수 있음.

//        try {
//            searchService.getProductsByKeyword(keyword, orderby, category);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        log.info("/member/login/ 로 들어왔어요 ㅋㅋ.");
        return "index";  // search.jsp 페이지로 이동시킴.
    }
}
