package facebook.controller;

import facebook.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {


    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        return send("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute LoginDTO loginDTO){
        return send("facebook");
    }
}
