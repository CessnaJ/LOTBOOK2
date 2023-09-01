package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.request.LoginDto;
import lotbook.lotbook.dto.request.SignupDto;
import lotbook.lotbook.service.MemberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
            } else if(loginUser == null){
                model.addAttribute("center", "signin");
                model.addAttribute("msg", "존재하지 않는 아이디입니다.");
            }
            else {
                model.addAttribute("center", "signin");
                model.addAttribute("msg", "email 또는 password가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping(value="/logout")
    public String logout(Model model, LoginDto loginDto, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("logincust") != null) {
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

    @GetMapping(value="/signup")
    public String signup(Model model, SignupDto signupDto){
        String password = bCryptPasswordEncoder.encode(signupDto.getPassword());
        signupDto.setPassword(password);

        Member signupInfo = Member.builder()
                .email(signupDto.getEmail())
                .hashedPwd(signupDto.getPassword())
                .name(signupDto.getName())
                .memberPhone(signupDto.getPhone())
                .zipcode(signupDto.getZipcode())
                .streetAddress(signupDto.getStreet_address_1()+" "+signupDto.getStreet_address_2())
                .addressDetail(signupDto.getAddress_detail())
                .build();

        try{
            memberService.register(signupInfo);
            model.addAttribute("center", "signin");
            model.addAttribute("msg", "회원가입을 축하합니다! 로그인을 진행해주세요 :)");

            /*Point point = null;
            int memberSeq = pointServiceImpl.getMemberSeq();

            point = Point.builder().point(1000).state(PointStateEnum.REGISTERED).memberSequence(memberSeq).build();
            pointServiceImpl.register(point);
            pointServiceImpl.modify(point);*/

        }catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

}
