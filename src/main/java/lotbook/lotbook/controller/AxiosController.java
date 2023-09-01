package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("rest")
public class AxiosController {
    private final MemberService memberService;

    @GetMapping(value="/api/checkDuplicateEmail")
    @ResponseBody
    public boolean checkDuplicateEmail(Model model, @RequestParam String email){
        boolean isDuplicate = memberService.isEmailDuplicate(email);
        return isDuplicate;
    }
}
