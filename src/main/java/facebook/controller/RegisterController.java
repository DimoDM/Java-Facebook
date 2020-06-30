package facebook.controller;


import facebook.dto.RegisterDTO;
import facebook.service.contract.RegisterService;
import facebook.service.implementation.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final RegisterServiceImpl registerService;

    @Autowired
    public RegisterController(RegisterServiceImpl registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/test")
    public String register(@ModelAttribute RegisterDTO registerDTO) {



        return "asd";
    }

}
