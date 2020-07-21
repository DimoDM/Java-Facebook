package facebook.service.implementation;

import facebook.component.EmailServiceImpl;
import facebook.dto.ResetPasswordDTO;
import facebook.entity.UserLoginData;
import facebook.repository.PasswordResetTokenRepository;
import facebook.repository.UserLoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Properties;

@Service
public class ResetPasswordService {

    private final UserLoginDataRepository userLoginDataRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailServiceImpl emailService;


    @Autowired
    public ResetPasswordService(UserLoginDataRepository userLoginDataRepository, PasswordResetTokenRepository passwordResetTokenRepository, EmailServiceImpl emailService) {
        this.userLoginDataRepository = userLoginDataRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailService = emailService;
    }


    public void changePassword(String email, String token, ResetPasswordDTO resetPasswordDTO){


    }

    @Transactional
    public void saveNewPasswordInDatabase(String password, String email) {
        UserLoginData user = userLoginDataRepository.findFirstByEmail(email);
        user.setPassword(password);
        userLoginDataRepository.save(user);
    }

    public String generateLink(String email, String token){
        return "localhost:8080/newPassword?token=" + token + "&email=" + email;
    }

}
