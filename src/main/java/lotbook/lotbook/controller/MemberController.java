package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.entity.Point;
import lotbook.lotbook.dto.request.LoginRequest;
import lotbook.lotbook.dto.request.SignupDto;
import lotbook.lotbook.enums.PointStateEnum;
import lotbook.lotbook.service.CartService;
import lotbook.lotbook.service.MemberService;
import lotbook.lotbook.service.PointService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final MemberService memberService;
    private final CartService cartService;
    private final PointService pointService;

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
                model.addAttribute("logincust", loginUser);
                session.setAttribute("logincust", loginUser);
            } else {
                model.addAttribute("center", "signin");
                if (loginUser == null) {
                    model.addAttribute("msg", "존재하지 않는 아이디입니다.");
                } else {
                    model.addAttribute("msg", "email 또는 password가 일치하지 않습니다.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("logincust") != null) {
            session.removeAttribute("logincust");
            session.invalidate();
        }
        /*try {
            model.addAttribute("BestSeller", productService.getBestseller());
            model.addAttribute("Latest", productService.getLatest());
            model.addAttribute("BigPoint", productService.getPoint());
            model.addAttribute("BigDiscount", productService.getDiscount());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        return "index";
    }

    @PostMapping(value = "/signup")
    public String signup(Model model, SignupDto signupDto) {
        String password = bCryptPasswordEncoder.encode(signupDto.getPassword());
        signupDto.setPassword(password);

        Member signupInfo = Member.builder()
                .email(signupDto.getEmail())
                .hashedPwd(signupDto.getPassword())
                .name(signupDto.getName())
                .memberPhone(signupDto.getPhone())
                .zipcode(signupDto.getZipcode())
                .streetAddress(signupDto.getStreet_address_1() + " " + signupDto.getStreet_address_2())
                .addressDetail(signupDto.getAddress_detail())
                .build();

        try {
            memberService.register(signupInfo);
            model.addAttribute("center", "signin");
            model.addAttribute("msg", "회원가입을 축하합니다! 로그인을 진행해주세요 :)");

            Point point = null;
            int memberSeq = pointService.getMemberSeq();

            point = Point.builder().point(1000).state(PointStateEnum.REGISTERED).memberSequence(memberSeq).build();
            pointService.register(point);
            pointService.modify(point);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping(value = "/myinfo")
    public String myinfo(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println((Member) session.getAttribute("logincust"));
        model.addAttribute("center", "member-info-login");
        model.addAttribute("memberSeq", ((Member) session.getAttribute("logincust")).getSequence());
        return "index";
    }

    @PostMapping(value = "/updateinfo")
    public String updateinfo(Model model, HttpSession session, @RequestParam("password") String password) {
        String email = ((Member) session.getAttribute("logincust")).getEmail();

        Member loginInfo = Member.builder()
                .email(email)
                .hashedPwd(password)
                .build();

        try {
            Member loginUser = memberService.get(loginInfo);
            if (bCryptPasswordEncoder.matches(password, loginUser.getHashedPwd())) {
                loginUser.setHashedPwd(null);
                session.setAttribute("logincust", loginUser);
                model.addAttribute("memberSeq", ((Member) session.getAttribute("logincust")).getSequence());
                model.addAttribute("center", "member-info");
            } else {
                model.addAttribute("center", "member-info-login");
                model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }

    @PostMapping(value="/updateinfoimpl")
    public String updateinfoimpl(Model model, HttpSession session, SignupDto newInfo, RedirectAttributes redirectAttributes){
        System.out.println(newInfo.toString());
        long sequence = (long)((Member)session.getAttribute("logincust")).getSequence();
        String password = bCryptPasswordEncoder.encode(newInfo.getPassword());
        newInfo.setPassword(password);
        Member newMemberInfo = Member.builder()
                .sequence(sequence)
                .hashedPwd(newInfo.getPassword())
                .name(newInfo.getName())
                .memberPhone(newInfo.getPhone())
                .zipcode(newInfo.getZipcode())
                .streetAddress(newInfo.getStreet_address_1()+" "+newInfo.getStreet_address_2())
                .addressDetail(newInfo.getAddress_detail())
                .build();

        try {
            memberService.modifyInfo(newMemberInfo);
            Member updatedMemberInfo = memberService.getById(sequence);
            session.setAttribute("logincust", updatedMemberInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        redirectAttributes.addAttribute("memberSeq", sequence);
        return "redirect:/main/mypage";
//        model.addAttribute("center", "member-info-login");
//        return "index";
    }


}
