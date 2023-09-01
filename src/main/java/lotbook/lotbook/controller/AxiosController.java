package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Cart;
import lotbook.lotbook.service.CartService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("rest")
public class AxiosController {

    private final CartService cartService;

    @GetMapping(value = "/api/changeCount")
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
            result = 0;
        }

        return result;
    }
}
