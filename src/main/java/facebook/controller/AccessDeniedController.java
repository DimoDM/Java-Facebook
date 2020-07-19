package facebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessDeniedController extends BaseController {

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized() {
        return send("unauthorized");
    }
}
