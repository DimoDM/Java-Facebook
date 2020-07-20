package facebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgottenPasswordController extends BaseController {

    @GetMapping("/newPassword")
    public ModelAndView changePassword(@RequestParam(value = "token", required = true) String token) {



        return send("facebook");
    }

}
