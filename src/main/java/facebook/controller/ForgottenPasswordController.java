package facebook.controller;

import facebook.dto.ResetPasswordDTO;
import facebook.service.implementation.EmailServiceImpl;
import facebook.service.implementation.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ForgottenPasswordController extends BaseController {

    private final ResetPasswordService resetPasswordService;
    private final EmailServiceImpl emailService;

    @Autowired
    public ForgottenPasswordController(ResetPasswordService resetPasswordService, EmailServiceImpl emailService) {
        this.resetPasswordService = resetPasswordService;
        this.emailService = emailService;
    }

    @GetMapping("/changePassword")
    public ModelAndView changePassword(@RequestParam(value = "token", required = true) String token,
                                       @RequestParam(value = "email", required = true) String email) throws IOException {

        emailService.sendCustomEmail("Dano da proraboti", "gogogospodinov69@gmail.com", "male, shsi udara guza ot tavana");

        return send("facebook");
    }

    @PostMapping("/sendMail")
    public void sendMail(@ModelAttribute ResetPasswordDTO resetPasswordDTO) {

    }

}
