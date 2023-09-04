package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final CartService cartService;

    @GetMapping
    public String DefaultMainPage(Model model, HttpServletRequest request) {
        model.addAttribute("center", "main");

        Member mem = (Member) request.getAttribute("logincust");
        Long memberSeq = (long) 0;
        if (mem != null) Long.parseLong(String.valueOf(mem.getSequence()));

        int cartCount = 0;
        try {
            cartCount = cartService.getCartCount(memberSeq);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("cartCount", cartCount);
        return "index";
    }
}
