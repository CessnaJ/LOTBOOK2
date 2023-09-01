package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.request.LoginRequest;
import lotbook.lotbook.service.CartService;
import lotbook.lotbook.service.MemberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final MemberService memberService;
    private final CartService cartService;

    @PostMapping(value = "/login")
    public String login(Model model, LoginRequest loginDto, HttpServletRequest request) {
        HttpSession session = request.getSession();

        model.addAttribute("center", "main");

        Member loginInfo = Member.builder().email(loginDto.getEmail()).hashedPwd(loginDto.getPassword()).build();

        try {
            Member loginUser = memberService.get(loginInfo);
            int cartCount = cartService.getCartCount(loginUser.getSequence());
            session.setAttribute("cartCount", cartCount);

            if (loginUser != null && bCryptPasswordEncoder.matches(loginDto.getPassword(), loginUser.getHashedPwd())) {
                session.setAttribute("logincust", loginUser);
            } else {
                model.addAttribute("center", "signin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}
