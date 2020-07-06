package facebook.controller;


import facebook.dto.RegisterDTO;
import facebook.entity.UserLoginData;
import facebook.service.contract.RegisterService;
import facebook.service.implementation.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController extends BaseController {

    private final RegisterServiceImpl registerService;

    @Autowired
    public RegisterController(RegisterServiceImpl registerService) {
        this.registerService = registerService;
    }

//    @GetMapping("/register")
//    public ModelAndView registerState(ModelAndView modelAndView){
//        modelAndView.setViewName("index.html");
//        return modelAndView;
//    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute RegisterDTO registerDTO) {


        registerService.register(registerDTO);
        return send("index.html");
    }

}
