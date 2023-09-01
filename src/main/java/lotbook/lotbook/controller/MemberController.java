package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.request.LoginDto;
import lotbook.lotbook.service.MemberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final MemberService memberService;

    @PostMapping(value = "/login")
    public String login(Model model, LoginDto loginDto) {
        model.addAttribute("center", "main");

        log.warn(loginDto.toString());
        Member loginInfo = Member.builder().email(loginDto.getEmail()).hashedPwd(loginDto.getPassword()).build();

        try {
            Member loginUser = memberService.get(loginInfo);

            if (loginUser != null && bCryptPasswordEncoder.matches(loginDto.getPassword(), loginUser.getHashedPwd())) {
                model.addAttribute("logincust", loginUser);
            } else {
                model.addAttribute("center", "signin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}
