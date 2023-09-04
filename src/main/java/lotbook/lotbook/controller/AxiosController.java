package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Cart;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.service.CartService;
import lotbook.lotbook.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AxiosController {

    private final CartService cartService;
    private final MemberService memberService;

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
}
