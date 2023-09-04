package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.entity.Review;
import lotbook.lotbook.dto.request.ReviewRequest;
import lotbook.lotbook.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(value="/update")
    public String update(Model model, ReviewRequest rinfo, RedirectAttributes redirectAttributes){

        Review reviewInfo = Review.builder()
                .sequence(rinfo.getSequence())
                .rating(rinfo.getRating())
                .comment(rinfo.getComment())
                .memberSequence(rinfo.getMemberSequence())
                .productSequence(rinfo.getProductSequence())
                .build();

        try {
            reviewService.modify(reviewInfo);
            model.addAttribute("center", "mypage");

        } catch (Exception e) {
            e.printStackTrace();
        }

        redirectAttributes.addAttribute("memberSeq", rinfo.getMemberSequence());
        return "redirect:/main/mypage";
    }

    @PostMapping(value="/delete")
    public String delete(Model model, @RequestParam("sequence") long sequence, HttpSession session, RedirectAttributes redirectAttributes){

        Review reviewInfo = Review.builder()
                .sequence(sequence)
                .build();

        try {
            reviewService.remove(reviewInfo);
            model.addAttribute("center", "mypage");

        } catch (Exception e) {
            e.printStackTrace();
        }

        long memberSeq = ((Member)session.getAttribute("logincust")).getSequence();
        redirectAttributes.addAttribute("memberSeq", memberSeq);
        return "redirect:/main/mypage";
    }
}
