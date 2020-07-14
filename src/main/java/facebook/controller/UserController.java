package facebook.controller;


import facebook.dto.RegisterDTO;
import facebook.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends BaseController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute RegisterDTO registerDTO) {


        userService.register(registerDTO);
        return send("facebook");
    }

    @GetMapping("/register")
    public ModelAndView registerGet() {

        return send("facebook");
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        return send("login");
    }
}
