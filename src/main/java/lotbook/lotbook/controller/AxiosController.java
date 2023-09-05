package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Cart;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.entity.Review;
import lotbook.lotbook.dto.request.AddToCartRequestDTO;
import lotbook.lotbook.dto.request.ReviewRequest;
import lotbook.lotbook.dto.response.SearchResult;
import lotbook.lotbook.service.CartService;
import lotbook.lotbook.service.MemberService;
import lotbook.lotbook.service.ReviewService;
import lotbook.lotbook.service.SearchService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AxiosController {

    private final CartService cartService;
    private final MemberService memberService;
    private final ReviewService reviewService;
    private final SearchService searchService;

    @GetMapping(value = "/changeCount")
    @ResponseBody
    public int changeCount(Model model, @RequestParam long sequence, @RequestParam long productSequence, @RequestParam long memberSequence, @RequestParam int count) {
        int result = 0;

        if (count < 1) {
            count = 1;
        }

        Cart cart = Cart.builder().count(count).productSequence(productSequence).sequence(sequence)
                .memberSequence(memberSequence).build();

        try {
            int changeResult = cartService.modify(cart);

            if (changeResult == 1) {
                result = count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @GetMapping(value="/checkDuplicateEmail")
    @ResponseBody
    public boolean checkDuplicateEmail(@RequestParam String email) {
        System.out.println("찾아옴!!");
        Member member = memberService.checkDuplicateEmail(email);
        return member!=null;
    }


    @PostMapping(value = "/addToCart")
    @ResponseBody
    public int addToCart(Model model, @RequestBody AddToCartRequestDTO addToCartRequestDTO) {
        int result = 0;
        int count = addToCartRequestDTO.getCount();

        if (addToCartRequestDTO.getCount() < 1) {
            count = 1;
        }

        Cart cart = Cart.builder()
                .count(count)
                .productSequence(addToCartRequestDTO.getProductSequence())
                .memberSequence(addToCartRequestDTO.getMemberSequence())
                .build();

        try {
            int changeResult = cartService.register(cart);

            if (changeResult == 1) {
                result = count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @PostMapping(value="/review/register")
    @ResponseBody
    public boolean register(Model model, ReviewRequest rinfo, HttpSession session){

        Review reviewInfo = Review.builder()
                .rating(rinfo.getRating())
                .comment(rinfo.getComment())
                .memberSequence(rinfo.getMemberSequence())
                .productSequence(rinfo.getProductSequence())
                .orderdetailSequence(rinfo.getOrderdetailSequence())
                .build();

        boolean result = true;
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
            result = false;
        }
        return result;
    }

    @GetMapping("/search")
    public SearchResult SearchKeyword(@RequestParam String keyword, @RequestParam(required = false) String orderby, @RequestParam(required = false) String category, Model model) {
        if (orderby == null) {
            orderby = "popular";
        }

        SearchResult searchResult = null;
        try {
            searchResult = searchService.getAllByKeyword(keyword, orderby, category);
            model.addAttribute("searchResult", searchResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return searchResult;

    }
}
