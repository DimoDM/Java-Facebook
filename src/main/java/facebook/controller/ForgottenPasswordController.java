package facebook.controller;

import facebook.dto.ForgottenPasswordDTO;
import facebook.dto.ResetPasswordDTO;
import facebook.service.implementation.EmailServiceImpl;
import facebook.service.implementation.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.UUID;

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

        if (resetPasswordService.checkIfLinkIsValid(token, email)){
            return send("changePassword", "userEmail", email);
        }

        return send("changePassword");
    }

    @PostMapping("/sendMail")
    public ModelAndView sendMail(@ModelAttribute ForgottenPasswordDTO forgottenPasswordDTO) {

        resetPasswordService.sendMail(forgottenPasswordDTO.getEmail());
        return send("facebook");
    }

    @PostMapping("/changePassword")
    public ModelAndView changePassword(@ModelAttribute ResetPasswordDTO resetPasswordDTO){

        resetPasswordService.saveNewPasswordInDatabase(resetPasswordDTO, resetPasswordDTO.getEmail());

        return send("login");
    }

}
