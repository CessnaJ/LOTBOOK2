package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.entity.Review;
import lotbook.lotbook.dto.request.ReviewRequest;
import lotbook.lotbook.service.MemberService;
import lotbook.lotbook.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final MemberService memberService;

    @PostMapping(value="/register")
    public String register(Model model, ReviewRequest rinfo, HttpSession session){

        Review reviewInfo = Review.builder()
                .rating(rinfo.getRating())
                .comment(rinfo.getComment())
                .memberSequence(rinfo.getMemberSequence())
                .productSequence(rinfo.getProductSequence())
                .orderdetailSequence(rinfo.getOrderdetailSequence())
                .build();

        try {
            // 리뷰 등록
            reviewService.register(reviewInfo);
            // 포인트 적립
            memberService.updatePoint(rinfo.getMemberSequence());

            Member loggedInUser = (Member) session.getAttribute("logincust");

            Member updatedUserInfo = memberService.get(Member.builder().email(loggedInUser.getEmail()).build());
            session.setAttribute("logincust", updatedUserInfo);
            model.addAttribute("center", "mypage");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }


}
