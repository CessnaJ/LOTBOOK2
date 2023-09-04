package lotbook.lotbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/page")
public class PageController  {
    @GetMapping(value = "/signin")
    public String DefaultSigninPage(Model model) {
        model.addAttribute("center", "signin");
        return "index";
    }
    @GetMapping(value = "/signup")
    public String DefaultSignupPage(Model model) {
        model.addAttribute("center", "signup");
        return "index";
    }

    @GetMapping(value = "/contact")
    public String DefaultContactPage(Model model) {
        model.addAttribute("center", "contact");
        return "index";
    }

}
