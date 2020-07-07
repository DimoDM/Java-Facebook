package facebook.controller;

import facebook.dto.LoginDTO;
import facebook.exception.InvalidLoginException;
import facebook.service.implementation.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {

    private final LoginServiceImpl loginService;

    @Autowired
    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        return send("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute LoginDTO loginDTO) throws InvalidLoginException {
        loginService.loginAuthentication(loginDTO);
        return send("facebook");
    }
}
